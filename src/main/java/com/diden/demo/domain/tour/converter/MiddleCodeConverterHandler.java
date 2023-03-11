package com.diden.demo.domain.tour.converter;

import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MiddleCodeConverterHandler implements TypeHandler<ServiceMiddleCode> {
  @Override
  public void setParameter(
      PreparedStatement ps, int i, ServiceMiddleCode parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, parameter.name());
  }

  @Override
  public ServiceMiddleCode getResult(ResultSet rs, String columnName) throws SQLException {
    return ServiceMiddleCode.codeToEnum(rs.getString(columnName));
  }

  @Override
  public ServiceMiddleCode getResult(ResultSet rs, int columnIndex) throws SQLException {
    return ServiceMiddleCode.codeToEnum(rs.getString(columnIndex));
  }

  @Override
  public ServiceMiddleCode getResult(CallableStatement cs, int columnIndex) throws SQLException {
    return ServiceMiddleCode.codeToEnum(cs.getString(columnIndex));
  }
}
