package com.example.freshcart.infrastructure.handlers;

import com.example.freshcart.domain.Role;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class RoleTypeHandler extends BaseTypeHandler<Role> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Role parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, parameter.name());
  }

  @Override
  public Role getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return Role.search(rs.getString(columnName));
  }

  @Override
  public Role getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return Role.search(rs.getString(columnIndex));
  }

  @Override
  public Role getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return Role.valueOf(cs.getString(columnIndex));
  }
}
