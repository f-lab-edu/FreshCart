package com.example.freshcart.stock.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.freshcart.config.TestRedisConfig;
import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.stock.application.StockReductionStrategy;
import com.example.freshcart.stock.domain.ProductStock;
import com.example.freshcart.stock.domain.ProductStockRepository;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;


@SpringBootTest(classes = TestRedisConfig.class)
public class GeneralStockReductionTest {

  @Autowired
  private ProductStockRepository productStockRepository;
  @Autowired
  private StockReductionStrategy stockReductionStrategy;

  @Autowired
  protected PlatformTransactionManager transactionManager;

  OrderItem orderItem;
  List<OrderItem> orderList;

  @BeforeEach
  public void init(){
    productStockRepository.save(new ProductStock(1L, 100, 1L));
    orderItem = new OrderItem(1L, 1);
//    orderList = new ArrayList<>();
//    orderList.add(orderItem);
  }
  @AfterEach
  public void after(){
    productStockRepository.deleteAll();
  }
  //@Transactional 없이 사용
  //재고 감소 시, db를 변경 하기 위해 transaction을 직접 만듦.
  //변경감지 사용하지 않는 테스트 - 아래 동시 요청의 경우, @transactional이 있는 상황에서 동작 x.
  @Test
  @DisplayName(("주문 시, 재고가 정상 차감됨"))
  public void stock_decrease(){
    //given
    TransactionStatus status =  transactionManager.getTransaction(null);
    //when
    stockReductionStrategy.reduceProductInventory(orderItem, 1);
    transactionManager.commit(status);

    //then
    ProductStock changedStock = productStockRepository.findByProductId(1L);
    assertEquals(99, changedStock.getQuantity());
  }

//  @Transactional
  @Test
  @DisplayName(("동시성이 보장되지 않는 코드이므로, 실패함"))
  public void 동시에_100개_요청() throws InterruptedException {
    int threadCount = 100;
    //비동기로 실행하는 작업 단순화하여 실행할 수 있게 도와주는 API
    ExecutorService executorService = Executors.newFixedThreadPool(32);
    //100개의 요청이 끝날때까지 기다려야 하는 상황. 다른 스레드에서 진행중인 작업이 완료 될 때까지 대기할 수 있도록 도와줌.
    CountDownLatch latch = new CountDownLatch(threadCount);

    for (int i=0;i<threadCount;i++){
      executorService.submit(()->{
        try{
          TransactionStatus status =  transactionManager.getTransaction(null);
          stockReductionStrategy.reduceProductInventory(orderItem, 1);
          transactionManager.commit(status);
        } finally {
          latch.countDown();
        }
      });
    }
    latch.await();

    ProductStock stock = productStockRepository.findByProductId(1L);
    //Expected: 100개 - 1*100 = 0 일 것이다.
    //그러나 경쟁상태가 일어났기 때문에, 테스트에 실패함.
    assertEquals(0L, stock.getQuantity());
  }
}