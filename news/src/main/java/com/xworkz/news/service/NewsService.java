package com.xworkz.news.service;

import com.xworkz.news.dto.LoginDto;
import com.xworkz.news.dto.RegisterDto;

public interface NewsService {

    String registerInService(RegisterDto registerationDto);

    RegisterDto findByEmailInService(String email);

    boolean findByPhInService(String phNo);

    String loginDetails(LoginDto loginDto);

}
