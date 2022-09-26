//package com.example.freshcart.product.infrastructure;
//
//import com.example.freshcart.product.domain.Option;
//import com.example.freshcart.product.domain.OptionRepository;
//import com.example.freshcart.product.infrastructure.OptionMapper;
//import java.util.List;
//
////작업 중이어서 주석 처리함.
//public class OptionMapperRepositoryAdaptor implements OptionRepository {
//
//  private final OptionMapper optionMapper;
//
//  public OptionMapperRepositoryAdaptor(
//      OptionMapper optionMapper) {
//    this.optionMapper = optionMapper;
//  }
//
//  @Override
//  public Option save(Option option) {
//    optionMapper.insert(option);
//    return option;
//  }
//
//  @Override
//  public List<Option> findByOptionGroupId(Long id) {
//  }
//}
