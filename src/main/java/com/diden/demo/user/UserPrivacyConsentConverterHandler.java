package com.diden.demo.user;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPrivacyConsentConverterHandler implements TypeHandler<UserVo.PrivacyConsent> {
  @Override
  public void setParameter(
      PreparedStatement ps, int i, UserVo.PrivacyConsent parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, parameter.getChoice());
  }

  @Override
  public UserVo.PrivacyConsent getResult(ResultSet rs, String columnName) throws SQLException {
    return UserVo.PrivacyConsent.getPrivacyConsentEnumType(rs.getString(columnName));
  }

  @Override
  public UserVo.PrivacyConsent getResult(ResultSet rs, int columnIndex) throws SQLException {
    return UserVo.PrivacyConsent.getPrivacyConsentEnumType(rs.getString(columnIndex));
  }

  @Override
  public UserVo.PrivacyConsent getResult(CallableStatement cs, int columnIndex)
      throws SQLException {
    return UserVo.PrivacyConsent.getPrivacyConsentEnumType(cs.getString(columnIndex));
  }
}
