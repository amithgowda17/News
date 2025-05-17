package com.xworkz.news.service;

import com.xworkz.news.dto.LoginDto;
import com.xworkz.news.dto.RegisterDto;
import com.xworkz.news.entity.LoginEntity;
import com.xworkz.news.entity.RegisterEntity;
import com.xworkz.news.repository.NewsRepository;
import com.xworkz.news.util.EmailSent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Slf4j
public class NewsServiceImpl implements NewsService{

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    EmailSent emailSent;

    @Override
    public String registerInService(RegisterDto registerationDto) {
        RegisterEntity registerEntity1 = newsRepository.findByEmail(registerationDto.getEmail());
        RegisterEntity registerEntity2 = newsRepository.findByPhNo(registerationDto.getPhNo());
        if (registerEntity1 != null || registerEntity2 != null) {
            return null;
        } else if (registerationDto.getPassword().equals(registerationDto.getConfirmPassword())) {
            registerationDto.setFileName("dummy.png");
            registerationDto.setFileContentType("image/png");
            RegisterEntity registerEntity = new RegisterEntity();
            BeanUtils.copyProperties(registerationDto, registerEntity);
            System.out.println(registerEntity);
            boolean isSaved = newsRepository.register(registerEntity);
            if (isSaved == true) {
                emailSent.mailSend(registerationDto.getEmail());
                log.info("Registering user {}",registerationDto.getEmail());
                return "Registration Successfull";
            }
            return null;

        } else {
            return null;
        }
    }

    @Override
    public RegisterDto findByEmailInService(String email) {
        RegisterEntity registerEntity = newsRepository.findByEmail(email);
        if (registerEntity != null) {
            RegisterDto registerationDto = new RegisterDto();
            BeanUtils.copyProperties(registerEntity, registerationDto);
            return registerationDto;
        }else {
            return null;
        }
    }

    @Override
    public boolean findByPhInService(String phNo) {
        RegisterEntity registerEntity = newsRepository.findByPhNo(phNo);
        if (registerEntity != null) {
            RegisterDto registerationDto = new RegisterDto();
            BeanUtils.copyProperties(registerEntity, registerationDto);
            return true;
        }
        return false;
    }

    @Override
    public String loginDetails(LoginDto loginDto) {
        RegisterDto registerationDto = findByEmailInService(loginDto.getEmail());
        RegisterEntity registerEntity = new RegisterEntity();
        if (registerationDto.getEmail() != null) {
            if (!(registerationDto.getPassword().equals(loginDto.getPassword()))) {
                BeanUtils.copyProperties(registerationDto, registerEntity);
                return "invalid password";
            } else {
                loginDto.setLoginDate(LocalDate.now().toString());
                loginDto.setLoginTime(LocalTime.now().toString());
                LoginEntity loginEntity = new LoginEntity();
                BeanUtils.copyProperties(loginDto, loginEntity);
                newsRepository.login(loginEntity);
                BeanUtils.copyProperties(registerationDto, registerEntity);
                return "login successfull";
            }
        } else {
            return null;
        }
    }
}
