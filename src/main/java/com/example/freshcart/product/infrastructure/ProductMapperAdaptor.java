package com.example.freshcart.product.infrastructure;

import com.example.freshcart.product.domain.Product;
import com.example.freshcart.product.domain.ProductRepository;
import java.util.List;

public class ProductMapperAdaptor implements ProductRepository {

  private final ProductMapper productMapper;

  public ProductMapperAdaptor(
      ProductMapper productMapper) {
    this.productMapper = productMapper;
  }


  @Override
  public Product save(Product product) {
    productMapper.insert(product);
    return product;
  }

  @Override
  public List<Product> findAll() {
    return productMapper.findAll();
  }

  @Override
  public Product findById(Long productId) {
    return productMapper.findById(productId);
  }

}
