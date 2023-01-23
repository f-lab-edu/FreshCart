//package com.example.freshcart.stock;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import com.example.freshcart.config.TestRedisConfig;
//import com.example.freshcart.order.application.OrderRegisterProcessor;
//import com.example.freshcart.order.domain.OrderItem;
//import com.example.freshcart.stock.application.StockReduceProcessor;
//import com.example.freshcart.stock.domain.OptionStockRepository;
//import com.example.freshcart.stock.domain.ProductStock;
//import com.example.freshcart.stock.domain.ProductStockRepository;
//import com.example.freshcart.stock.infrastructure.GeneralStockReduction;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest(classes = TestRedisConfig.class)
//public class OrderServiceTest {
//
//  @Autowired
//  private GeneralStockReduction generalStockReduction;
//  @Autowired
//  private ProductStockRepository productStockRepository;
//  @Autowired
//  private OptionStockRepository optionStockRepository;
//
//  @Autowired
//  private StockReduceProcessor stockReduceProcessor;
//
//  OrderItem orderItem;
//  List<OrderItem> list;
//
//  @BeforeEach
//  public void before(){
//    orderItem = new OrderItem(1L, 100);
//    list = new ArrayList<>();
//    list.add(orderItem);
//    productStockRepository.save(new ProductStock(orderItem.getProductId(), orderItem.getCount(), 1L));
//  }
//  @AfterEach
//  public void after(){
//    productStockRepository.deleteAll();
//  }
//
//  @Transactional
//  @Test
//  @DisplayName(("정상적으로 주문을 차감할 수 있다."))
//  public void stock_decrease(){
//    stockReduceProcessor.reduceInventory(list);
////    generalStockReduction.checkProductInventory(orderItem, 1);
//    ProductStock stock = productStockRepository.findByProductId(1L);
//    assertEquals(99, stock.getQuantity());
//  }
//}