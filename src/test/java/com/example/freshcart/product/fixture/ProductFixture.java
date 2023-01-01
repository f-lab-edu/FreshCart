package com.example.freshcart.product.fixture;

import com.example.freshcart.product.domain.Product.Status;
import com.example.freshcart.product.presentation.request.OptionDetailRegister;
import com.example.freshcart.product.presentation.request.OptionGroupRegister;
import com.example.freshcart.product.presentation.request.OptionSet;
import com.example.freshcart.product.presentation.request.OptionSet.OptionSetBuilder;
import com.example.freshcart.product.presentation.request.ProductRegisterRequest;

import java.util.Arrays;


/**
 * 테스트 코드 실행에 필요한 객체 준비
 */

public class ProductFixture {

  public static ProductRegisterRequest.ProductRegisterRequestBuilder aProductRegisterRequest() {
    return ProductRegisterRequest.builder()
        .name("샐러드").
        price(2000).
        status(Status.AVAILABLE).
        description("맞춤 구성 샐러드").
        singleType(false).
        categoryId(1).
        optionSet(Arrays.asList(anOptionSet().build()));
  }

  public static OptionSetBuilder anOptionSet() {
    return OptionSet.builder().
        optionGroupRegister(anOptionGroupRegister().build()).
        optionDetailRegisterList(Arrays.asList(
            anOptionDetailRegister()
                .build(),
            anOptionDetailRegister()
                .name("60g")
                .price(2000)
                .build()))
        .optionGroupRegister(
            anOptionGroupRegister().optionGroupName("토핑").requiredOption(false).exclusive(false)
                .minimumOrder(1).maximumOrder(2).build()).
        optionDetailRegisterList(Arrays.asList(
            anOptionDetailRegister()
                .name("견과류")
                .price(1000)
                .build(),
            anOptionDetailRegister()
                .name("크랜베리")
                .price(500)
                .build()));

  }

  public static OptionGroupRegister.OptionGroupRegisterBuilder anOptionGroupRegister() {
    return OptionGroupRegister.builder().
        optionGroupName("중량").
        requiredOption(true).
        exclusive(true).
        minimumOrder(1).
        maximumOrder(1);
  }

  public static OptionDetailRegister.OptionDetailRegisterBuilder anOptionDetailRegister() {
    return OptionDetailRegister.builder()
        .name("30g")
        .price(0);
  }

  public static ProductRegisterRequest.ProductRegisterRequestBuilder anotherProductRegisterRequest() {
    return ProductRegisterRequest.builder()
        .name("샤브샤브밀키트").
        price(5000).
        status(Status.AVAILABLE).
        description("맛나요").
        singleType(false).
        categoryId(4).
        optionSet(Arrays.asList(anotherOptionSet().build()));
  }

  public static OptionSetBuilder anotherOptionSet() {
    return OptionSet.builder().
        optionGroupRegister(anOptionGroupRegister().build()).
        optionDetailRegisterList(Arrays.asList(
            anOptionDetailRegister()
                .name("100g")
                .price(0)
                .build(),
            anOptionDetailRegister()
                .name("200g")
                .price(4000)
                .build()))
        .optionGroupRegister(
            anOptionGroupRegister().optionGroupName("야채").requiredOption(false).exclusive(false)
                .minimumOrder(1).maximumOrder(3).build()).
        optionDetailRegisterList(Arrays.asList(
            anOptionDetailRegister()
                .name("청경채")
                .price(1000)
                .build(),
            anOptionDetailRegister()
                .name("알배추")
                .price(500)
                .build(),
            anOptionDetailRegister()
                .name("버섯")
                .price(2000)
                .build()));


  }


}
