package com.example.freshcart.optionstock.application;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.global.utils.VerifyUserUtils;
import com.example.freshcart.optionstock.application.command.OptionStockAddCommand;
import com.example.freshcart.optionstock.application.command.OptionStockUpdateCommand;
import com.example.freshcart.optionstock.domain.OptionStock;
import com.example.freshcart.optionstock.domain.OptionStockRepository;
import com.example.freshcart.optionstock.domain.exception.OptionStockExistsExcecption;
import com.example.freshcart.product.domain.Option;
import com.example.freshcart.product.domain.OptionRepository;
import com.example.freshcart.user.application.command.SignupCommand;
import com.example.freshcart.user.domain.User;
import com.example.freshcart.user.domain.exception.EmailExistsException;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class OptionStockManager {

  private final OptionStockRepository optionStockRepository;
  private final OptionRepository optionRepository;


  public OptionStockManager(
      OptionStockRepository optionStockRepository,
      OptionRepository optionRepository) {
    this.optionStockRepository = optionStockRepository;
    this.optionRepository = optionRepository;
  }

  public void addInventory(LoginUser user, List<OptionStockAddCommand> commandList) {
    for(OptionStockAddCommand command: commandList){
      Long optionId = command.getOptionId();
      if (optionStockRepository.findByOptionId(optionId) != null) {
        throw new OptionStockExistsExcecption();
      }
      Option option = optionRepository.findById(command.getOptionId());
      VerifyUserUtils.verifyOwner(user, option.getSellerId());
      OptionStock optionStock = OptionStockAddCommand.of(user.getUserId(), optionId, command);
      optionStockRepository.save(optionStock);
    }
      // 이메일 중복 체크

  }

  //판매자가 임의로 수량을 바꾸고 싶을 때
  public void updateInventory(LoginUser user, Long optionStockId, OptionStockUpdateCommand command) {
    OptionStock optionStock = optionStockRepository.findById(optionStockId);
    VerifyUserUtils.verifyOwner(user, optionStock.getSellerId());
    optionStock.changeStock(command.getStock());
    optionStockRepository.save(optionStock);
  }
}
