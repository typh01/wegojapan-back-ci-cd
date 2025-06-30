package com.kh.avengers.auth.model.service;


import com.kh.avengers.auth.model.vo.Login;
import com.kh.avengers.common.dto.RequestData;

import jakarta.validation.Valid;

public interface AuthService {

  RequestData login(Login member);


}
