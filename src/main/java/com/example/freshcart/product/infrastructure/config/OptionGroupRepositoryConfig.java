package com.example.freshcart.product.infrastructure.config;

import com.example.freshcart.product.domain.OptionGroupRepository;
import com.example.freshcart.product.infrastructure.OptionGroupMapper;
import com.example.freshcart.product.infrastructure.OptionGroupMapperRepositoryAdaptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OptionGroupRepositoryConfig {

  @Bean
  public OptionGroupRepository optionGroupRepository(OptionGroupMapper optionGroupMapper){
    return new OptionGroupMapperRepositoryAdaptor(optionGroupMapper);
  }
}
