package com.diden.demo.domain.tour.converter;

import com.diden.demo.domain.tour.enums.ServiceHighCode;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HighCodeConverterHandler implements TypeHandler<ServiceHighCode> {
  @Override
  public void setParameter(
      PreparedStatement ps, int i, ServiceHighCode parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, parameter.name());
  }

  @Override
  public ServiceHighCode getResult(ResultSet rs, String columnName) throws SQLException {
    return ServiceHighCode.codeToEnum(rs.getString(columnName));
  }

  @Override
  public ServiceHighCode getResult(ResultSet rs, int columnIndex) throws SQLException {
    return ServiceHighCode.codeToEnum(rs.getString(columnIndex));
  }

  @Override
  public ServiceHighCode getResult(CallableStatement cs, int columnIndex) throws SQLException {
    return ServiceHighCode.codeToEnum(cs.getString(columnIndex));
  }
}
