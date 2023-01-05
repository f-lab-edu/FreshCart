package com.example.freshcart.product.infrastructure.jdbc;

import com.example.freshcart.product.domain.OptionGroup;
import com.example.freshcart.product.domain.Product;
import com.example.freshcart.product.presentation.request.OptionGroupRegister;
import com.example.freshcart.product.presentation.request.OptionSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * OptionGroup, Option, Product를 따로 저장하는 방식과 달리 한번에 저장하므로 인터페이스를 구현하지 않고 따로 만들었음. Product,
 * OptionGroup, Option 등 모두를 저장함. Single인 경우 Product만, OptionGroup인 경우 Option만 저장함. 따라서 Service도 수정할
 * 필요 있음. - 다른 클래스로. ServiceV2 (jdbc 사용해서 한 번에 저장하는0
 */

@RequiredArgsConstructor
public class JdbcProductRepository {

  private final JdbcTemplate jdbcTemplate;

  public void batchInsertSingleProduct(Product product) {
//            + returning product_id;
  }


  public void batchInsertOptionalProducts(Long userId, Product product) {
    Connection con = null;
    PreparedStatement ps = null;
    String sql = "Insert Into product(name, price, status, description, single_type, category_id, seller_id) Values(?, ?, ?, ?, ?, ?, ?)" ;
    String sql2 =
       "Insert Into option_group (name, is_required, exclusive, minimum_order, maximum_order, product_id, seller_id) Values(?, ?, ?, ?, ?, ?, ?)" ;
//    Values (#{optionGroupName}, #{isRequired}, #{exclusive}, #{minimumOrder}, #{maximumOrder}, #{productId}, #{sellerId})";
    //여러 쿼리는 어떻게 날릴 수 있을까?
    //쿼리부터 만들자.
    //foreach가 안된다면, 다시 만들 것.
    String sql3 = "    insert into `option` (name, price, option_group_id, seller_id)"
        + "    values"
        + "    <foreach collection=\"list\" index=\"index\" item=\"option\" separator=\",\">\n"
        + "    (#{option.name}, #{option.price}, #{option.optionGroupId}, #{option.sellerId})\n"
        + "    </foreach>";
    jdbcTemplate.batchUpdate("");



    try {
      //application.yml 에 설정된 클래스로 드라이버/커넥션 가져올 수 없는지 알아보기.
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/freshcart?characterEncoding=UTF-8", "admin", "password");
      ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      ps.setString(1, product.getName());
      ps.setInt(2, product.getPrice());
      ps.setObject(3, product.getStatus());
      ps.setString(4, product.getDescription());
      ps.setBoolean(5, product.isSingleType());
      ps.setInt(6, product.getCategoryId());
      ps.setLong(7, product.getSellerId());

      ps.addBatch();
      ps.clearParameters();

      //Product 생성 시 key 가져오기
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
//            Values (#{name}, #{price}, #{status}, #{description}, #{singleType}, #{categoryId}, #{sellerId})"


  }


}
