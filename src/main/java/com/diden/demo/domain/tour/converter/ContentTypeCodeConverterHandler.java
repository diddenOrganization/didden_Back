package com.diden.demo.domain.tour.converter;

import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContentTypeCodeConverterHandler implements TypeHandler<ServiceContentTypeCode> {
  @Override
  public void setParameter(
      PreparedStatement ps, int i, ServiceContentTypeCode parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, parameter.name());
  }

  @Override
  public ServiceContentTypeCode getResult(ResultSet rs, String columnName) throws SQLException {
    return ServiceContentTypeCode.codeToEnum(Integer.parseInt(rs.getString(columnName)));
  }

  @Override
  public ServiceContentTypeCode getResult(ResultSet rs, int columnIndex) throws SQLException {
    return ServiceContentTypeCode.codeToEnum(Integer.parseInt(rs.getString(columnIndex)));
  }

  @Override
  public ServiceContentTypeCode getResult(CallableStatement cs, int columnIndex)
      throws SQLException {
    return ServiceContentTypeCode.codeToEnum(Integer.parseInt(cs.getString(columnIndex)));
  }
}
