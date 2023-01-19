package com.example.freshcart.optionstock.application;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.global.utils.VerifyUserUtils;
import com.example.freshcart.optionstock.application.command.OptionStockAddCommand;
import com.example.freshcart.optionstock.application.command.OptionStockUpdateCommand;
import com.example.freshcart.optionstock.application.command.ProductStockAddCommand;
import com.example.freshcart.optionstock.application.command.ProductStockUpdateCommand;
import com.example.freshcart.optionstock.domain.OptionStock;
import com.example.freshcart.optionstock.domain.OptionStockRepository;
import com.example.freshcart.optionstock.domain.ProductStock;
import com.example.freshcart.optionstock.domain.ProductStockRepository;
import com.example.freshcart.optionstock.domain.exception.OptionStockExistsExcecption;
import com.example.freshcart.optionstock.domain.exception.ProductStockExistsExcecption;
import com.example.freshcart.product.domain.Option;
import com.example.freshcart.product.domain.OptionRepository;
import com.example.freshcart.product.domain.Product;
import com.example.freshcart.product.domain.ProductRepository;
import com.example.freshcart.user.application.command.SignupCommand;
import com.example.freshcart.user.domain.User;
import com.example.freshcart.user.domain.exception.EmailExistsException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OptionStockManager {

  private final OptionStockRepository optionStockRepository;
  private final OptionRepository optionRepository;
  private final ProductStockRepository productStockRepository;
  private final ProductRepository productRepository;

  public void addProductInventory(LoginUser user, List<ProductStockAddCommand> commandList) {
    for (ProductStockAddCommand command : commandList) {
      Long productId = command.getProductId();
      if (productStockRepository.findByProductId(productId) != null) {
        throw new ProductStockExistsExcecption();
      }
      Product product = productRepository.findById(productId);
      VerifyUserUtils.verifyOwner(user, product.getSellerId());
      ProductStock productStock = ProductStockAddCommand.of(productId, command, user.getUserId());
      productStockRepository.save(productStock);
    }

  }

  //판매자가 임의로 수량을 바꾸고 싶을 때
  public void updateProductInventory(LoginUser user, Long productStockId,
      ProductStockUpdateCommand command) {
    ProductStock productStock = productStockRepository.findById(productStockId);
    VerifyUserUtils.verifyOwner(user, productStock.getSellerId());
    productStock.changeStock(command.getStock());
    productStockRepository.save(productStock);
  }

  public void addInventory(LoginUser user, List<OptionStockAddCommand> commandList) {
    for (OptionStockAddCommand command : commandList) {
      Long optionId = command.getOptionId();
      if (optionStockRepository.findByOptionId(optionId) != null) {
        throw new OptionStockExistsExcecption();
      }
      Option option = optionRepository.findById(command.getOptionId());
      VerifyUserUtils.verifyOwner(user, option.getSellerId());
      OptionStock optionStock = OptionStockAddCommand.of(user.getUserId(), optionId, command);
      optionStockRepository.save(optionStock);
    }

  }

  //판매자가 임의로 수량을 바꾸고 싶을 때
  public void updateInventory(LoginUser user, Long optionStockId,
      OptionStockUpdateCommand command) {
    OptionStock optionStock = optionStockRepository.findById(optionStockId);
    VerifyUserUtils.verifyOwner(user, optionStock.getSellerId());
    optionStock.changeStock(command.getStock());
    optionStockRepository.save(optionStock);
  }

}
