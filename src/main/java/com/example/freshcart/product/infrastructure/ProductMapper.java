package com.example.freshcart.product.infrastructure;

import com.example.freshcart.product.domain.Product;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

  void insert(Product product);

  List<Product> findAll();

  Product findById(Long productId);
}
