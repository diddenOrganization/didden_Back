package com.diden.demo.domain.user.converter;

import com.diden.demo.domain.user.enums.GenderEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserGenderConverterHandler implements TypeHandler<GenderEnum> {

  @Override
  public void setParameter(PreparedStatement ps, int i, GenderEnum parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, parameter.getGender());
  }

  @Override
  public GenderEnum getResult(ResultSet rs, String columnName) throws SQLException {
    return GenderEnum.getGenderEnumType(rs.getString(columnName));
  }

  @Override
  public GenderEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
    return GenderEnum.getGenderEnumType(rs.getString(columnIndex));
  }

  @Override
  public GenderEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
    return GenderEnum.getGenderEnumType(cs.getString(columnIndex));
  }
}
