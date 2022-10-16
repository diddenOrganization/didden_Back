package com.diden.demo.user;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserGenderConverterHandler implements TypeHandler<UserVo.Gender> {

  @Override
  public void setParameter(PreparedStatement ps, int i, UserVo.Gender parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, parameter.getGender());
  }

  @Override
  public UserVo.Gender getResult(ResultSet rs, String columnName) throws SQLException {
    return UserVo.Gender.getGenderEnumType(rs.getString(columnName));
  }

  @Override
  public UserVo.Gender getResult(ResultSet rs, int columnIndex) throws SQLException {
    return UserVo.Gender.getGenderEnumType(rs.getString(columnIndex));
  }

  @Override
  public UserVo.Gender getResult(CallableStatement cs, int columnIndex) throws SQLException {
    return UserVo.Gender.getGenderEnumType(cs.getString(columnIndex));
  }
}
