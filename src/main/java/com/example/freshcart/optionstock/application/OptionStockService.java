package com.example.freshcart.optionstock.application;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.global.utils.VerifyUserUtils;
import com.example.freshcart.optionstock.application.command.OptionStockAddCommand;
import com.example.freshcart.optionstock.application.command.OptionStockUpdateCommand;
import com.example.freshcart.optionstock.domain.OptionStock;
import com.example.freshcart.optionstock.domain.OptionStockRepository;
import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.product.application.ProductService;
import com.example.freshcart.product.domain.Option;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OptionStockService {

  private final OptionStockRepository optionStockRepository;
  private final ProductService productService;

  public OptionStockService(
      OptionStockRepository optionStockRepository,
      ProductService productService) {
    this.optionStockRepository = optionStockRepository;
    this.productService = productService;
  }

  public void addInventory(LoginUser user, Long optionId, OptionStockAddCommand command) {
    Option option = productService.getOption(optionId);
    VerifyUserUtils.verifyOwner(user, option.getSellerId());
    OptionStock optionStock = OptionStockAddCommand.of(user.getUserId(), optionId, command);
    optionStockRepository.save(optionStock);
  }

  public void updateInventory(LoginUser user, Long optionStockId, OptionStockUpdateCommand command) {
    OptionStock optionStock = optionStockRepository.findById(optionStockId);
    VerifyUserUtils.verifyOwner(user, optionStock.getSellerId());
    optionStock.changeStock(command.getStock());
    optionStockRepository.save(optionStock);
  }

  //주문으로 Stock이 줄어듦.
  public void reduceStock(List<OrderItem> orderItemList){
    //재고가 없다면, 재고 없다 exception
    //Order orderItem의 Id 확인
  }
}
