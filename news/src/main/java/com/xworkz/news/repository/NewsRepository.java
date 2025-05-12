package com.xworkz.news.repository;

import com.xworkz.news.entity.LoginEntity;
import com.xworkz.news.entity.RegisterEntity;

public interface NewsRepository {

    boolean register(RegisterEntity registerEntity);

    RegisterEntity findByEmail(String email);

    RegisterEntity findByPhNo(String phNo);

    boolean login(LoginEntity loginEntity);

}
