package com.example.freshcart.legacy.jdbc;

import com.example.freshcart.product.domain.Option;
import com.example.freshcart.product.domain.OptionGroup;
import com.example.freshcart.product.domain.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * OptionGroup, Option, Product를 따로 저장하는 방식과 달리 한번에 저장하므로 인터페이스를 구현하지 않고 따로 만들었음. Product,
 * OptionGroup, Option 등 모두를 저장함. Single인 경우 Product만, OptionGroup인 경우 Option만 저장.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class JdbcProductRepository {

  public void batchInsertOptionalProducts(Long userId, Product product) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;
    boolean success = false; // 예외가 터지지 않았는지 감지하는 지표
    //1. 쿼리문 준비
    String sql = "Insert Into product(name, price, status, description, single_type, category_id, seller_id) Values(?, ?, ?, ?, ?, ?, ?)";
    String sql2 = "Insert Into option_group(name, is_required, exclusive, minimum_order, maximum_order, product_id, seller_id) Values(?, ?, ?, ?, ?, ?, ?)";
    String sql3 = "Insert Into `option`(name, price, option_group_id, seller_id) Values(?, ?, ?, ?)";

    try {

      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/freshcart?characterEncoding=UTF-8", "admin", "password");
      con.setAutoCommit(false); //기본 설정은 한 SQL 당 자동 COMMIT된다. 여러 SQL 을 COMMIT 하려면 이 설정을 FALSE 로.

      ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      ps.setString(1, product.getName());
      ps.setInt(2, product.getPrice());
      ps.setString(3, product.getStatus().name());
      ps.setString(4, product.getDescription());
      ps.setBoolean(5, product.isSingleType());
      ps.setInt(6, product.getCategoryId());
      ps.setLong(7, product.getSellerId());

      ps.executeUpdate();
      ResultSet productId = ps.getGeneratedKeys();
      boolean result = productId.next();
      log.info(result + "result 결과가 true면, cursor 를 앞으로 옮겨줌.");
      ps.clearParameters();

      for (OptionGroup optionGroup : product.getOptionGroupSpecs()) {
        ps = con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, optionGroup.getOptionGroupName());
        ps.setBoolean(2, optionGroup.isRequired());
        ps.setBoolean(3, optionGroup.isExclusive());
        ps.setInt(4, optionGroup.getMinimumOrder());
        ps.setInt(5, optionGroup.getMaximumOrder());
        log.info(productId.getLong(1) + " productId");
        ps.setLong(6, productId.getLong(1));
        ps.setLong(7, userId);
        ps.executeUpdate();
        ResultSet optionGroupId = ps.getGeneratedKeys();
        boolean result2 = optionGroupId.next(); //Returns: true if the new current row is valid; false if there are no more rows
        ps.clearParameters();

        for (Option option : optionGroup.getOptions()) {
          ps = con.prepareStatement(sql3);
          ps.setString(1, option.getName());
          ps.setInt(2, option.getPrice());
          log.info(optionGroupId.getLong(1) + " optionGroupId");
          ps.setLong(3, optionGroupId.getLong(1));
          ps.setLong(4, userId);
          ps.addBatch();
        }
        ps.executeBatch();
        con.commit();
      }

    } catch (Exception e) {
      e.printStackTrace();
      success = false;
      //하나라도 커밋이 안되었다면 rollback 필요.
    } finally {
      if (ps != null) {
        ps.close();
      }
      if (con != null) {
        if (success) {
          con.commit();
        } else {
          con.rollback();
        }
        con.close();
      }
    }
  }
}

