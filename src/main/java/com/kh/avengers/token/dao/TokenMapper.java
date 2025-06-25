package com.kh.avengers.token.dao;

import com.kh.avengers.token.vo.RefreshToken;

public interface TokenMapper {

  void saveToken(RefreshToken token);

  RefreshToken findByToken(RefreshToken token);

}
