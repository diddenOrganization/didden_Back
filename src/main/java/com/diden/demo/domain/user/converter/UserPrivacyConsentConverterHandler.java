package com.diden.demo.domain.user.converter;

import com.diden.demo.domain.user.enums.PrivacyConsentEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPrivacyConsentConverterHandler implements TypeHandler<PrivacyConsentEnum> {
  @Override
  public void setParameter(
          PreparedStatement ps, int i, PrivacyConsentEnum parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, parameter.getChoice());
  }

  @Override
  public PrivacyConsentEnum getResult(ResultSet rs, String columnName) throws SQLException {
    return PrivacyConsentEnum.getPrivacyConsentEnumType(rs.getString(columnName));
  }

  @Override
  public PrivacyConsentEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
    return PrivacyConsentEnum.getPrivacyConsentEnumType(rs.getString(columnIndex));
  }

  @Override
  public PrivacyConsentEnum getResult(CallableStatement cs, int columnIndex)
      throws SQLException {
    return PrivacyConsentEnum.getPrivacyConsentEnumType(cs.getString(columnIndex));
  }
}
