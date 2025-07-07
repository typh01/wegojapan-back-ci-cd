package com.kh.avengers.common.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MyBatis에서 Java의 boolean 타입을 DB의 'Y'/'N' CHAR 타입으로 변환해주는 핸들러
 */
@MappedTypes(Boolean.class)
public class BooleanYnTypeHandler extends BaseTypeHandler<Boolean> {

  /**
   * Java의 boolean 값을 DB에 저장할 때 'Y' 또는 'N'으로 변환
   */
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter ? "Y" : "N");
  }

  /**
   * DB의 'Y'/'N' 값을 Java의 boolean으로 변환
   */
  @Override
  public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return "Y".equals(rs.getString(columnName));
  }

  @Override
  public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return "Y".equals(rs.getString(columnIndex));
  }

  @Override
  public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return "Y".equals(cs.getString(columnIndex));
  }
}
