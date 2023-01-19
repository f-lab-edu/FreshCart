package com.example.freshcart.optionstock.presentation;

import com.example.freshcart.authentication.Role;
import com.example.freshcart.authentication.annotation.AuthenticatedUser;
import com.example.freshcart.authentication.annotation.Authentication;
import com.example.freshcart.authentication.annotation.LoginCheck;
import com.example.freshcart.authentication.application.LoginUser;

import static java.util.stream.Collectors.toList;
import com.example.freshcart.optionstock.application.OptionStockManager;
import com.example.freshcart.optionstock.presentation.request.OptionStockAddRequest;
import com.example.freshcart.optionstock.presentation.request.OptionStockUpdateRequest;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Inventory는 ProductOption 별로 적용한다 재고를 추가하고 변경할 권한은 판매자만 갖고 있다.
 */

@Slf4j
@RestController
@RequestMapping("/stocks")
public class OptionStockController {

  private final OptionStockManager optionStockManager;

  public OptionStockController(
      OptionStockManager optionStockManager) {
    this.optionStockManager = optionStockManager;
  }

  @Authentication(authority = Role.SELLER)
  @LoginCheck
  @PostMapping
  public void addStock(@AuthenticatedUser LoginUser user, @RequestBody List<OptionStockAddRequest> request) {
    optionStockManager.addInventory(user, request.stream().map(OptionStockAddRequest::toCommand).collect(
        toList()));
  }

  @Authentication(authority = Role.SELLER)
  @LoginCheck
  @PatchMapping("/{optionStockId}")
  public void changeStock(@AuthenticatedUser LoginUser user, @PathVariable Long optionStockId,
      @RequestBody OptionStockUpdateRequest request) {
    optionStockManager.updateInventory(user, optionStockId, request.toCommand());
  }
}
