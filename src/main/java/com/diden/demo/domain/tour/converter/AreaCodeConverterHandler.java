package com.diden.demo.domain.tour.converter;

import com.diden.demo.domain.tour.definition.AreaCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaCodeConverterHandler implements TypeHandler<AreaCode> {
  @Override
  public void setParameter(PreparedStatement ps, int i, AreaCode parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, parameter.getCodeName());
  }

  @Override
  public AreaCode getResult(ResultSet rs, String columnName) throws SQLException {
    final String prevAreaCode = rs.getString(columnName);
    if(StringUtils.isBlank(prevAreaCode)) {
      return AreaCode.NONE;
    } else {
      return AreaCode.findArea(Integer.parseInt(rs.getString(columnName)));
    }
  }

  @Override
  public AreaCode getResult(ResultSet rs, int columnIndex) throws SQLException {
    return AreaCode.findArea(Integer.parseInt(rs.getString(columnIndex)));
  }

  @Override
  public AreaCode getResult(CallableStatement cs, int columnIndex) throws SQLException {
    return AreaCode.findArea(Integer.parseInt(cs.getString(columnIndex)));
  }
}
