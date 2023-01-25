package com.example.freshcart.stock.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.freshcart.config.TestRedisConfig;
import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.stock.domain.ProductStock;
import com.example.freshcart.stock.domain.ProductStockRepository;
import com.example.freshcart.stock.infrastructure.stockreduction.OptimisticLockStockFacade;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

@SpringBootTest(classes = TestRedisConfig.class)
class OptimisticLockStockFacadeTest {

  @Autowired
  private ProductStockRepository productStockRepository;
  @Autowired
  private OptimisticLockStockFacade optimisticLockStockFacade;
  @Autowired
  private PlatformTransactionManager transactionManager;

  OrderItem orderItem;

  @BeforeEach
  public void init(){
    productStockRepository.save(new ProductStock(1L, 100, 1L));
    orderItem = new OrderItem(1L, 1);
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
  public void stock_decrease() throws InterruptedException {
    //given
    TransactionStatus status =  transactionManager.getTransaction(null);
    //when
    optimisticLockStockFacade.reduceProductInventory(orderItem, 1);
    transactionManager.commit(status);

    //then
    ProductStock changedStock = productStockRepository.findByProductId(1L);
    assertEquals(99, changedStock.getQuantity());
  }

  @Test
  @DisplayName(("동시에 100개 요청"))
  public void 동시에_100개_요청() throws InterruptedException {
    //given
    int threadCount = 100;
    ExecutorService executorService = Executors.newFixedThreadPool(32);
    CountDownLatch latch = new CountDownLatch(threadCount);

    //when
    IntStream.range(0,100).forEach(e -> executorService.submit(()->{
      try{
        optimisticLockStockFacade.reduceProductInventory(orderItem, 1);
      } catch(InterruptedException ex) {
        throw new RuntimeException(ex);
      }finally {
        latch.countDown();
      }
    }));

    latch.await();

    ProductStock stock = productStockRepository.findByProductId(1L);
    assertEquals(0L, stock.getQuantity());
  }
}