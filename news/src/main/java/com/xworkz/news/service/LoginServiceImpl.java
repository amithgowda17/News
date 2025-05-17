package com.xworkz.news.service;

import com.xworkz.news.dto.RegisterDto;
import com.xworkz.news.entity.RegisterEntity;
import com.xworkz.news.repository.LoginRepository;
import com.xworkz.news.util.EmailSent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService{

    private static String UPLOADED_FOLDER = "D://Project//file_upload//";


    @Autowired
    EmailSent emailSent;

    @Autowired
    NewsService newsService;

    @Autowired
    LoginRepository loginRepository;

    @Override
    public boolean generateOtpInService(String email, String otp) {
        RegisterDto registerationDto = newsService.findByEmailInService(email);
        if (registerationDto != null) {
            String otpSave = emailSent.mailSend(email);
            registerationDto.setOtp(otpSave);
            log.info("registerationDto.getOtp()====" + registerationDto.getOtp());
            RegisterEntity registerEntity = new RegisterEntity();
            BeanUtils.copyProperties(registerationDto, registerEntity);
            loginRepository.updateProfile(registerEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyOtp(String email, String otpEntered) {
        RegisterDto registerationDto = newsService.findByEmailInService(email);
        if (registerationDto != null) {
            if (otpEntered.equals(registerationDto.getOtp())) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean updatePasswordInService(String email, String password, String confirmPassword) {
        RegisterDto registerationDto = newsService.findByEmailInService(email);
        if (registerationDto != null) {
            if (password.equals(confirmPassword)) {
                registerationDto.setPassword(password);
                RegisterEntity registerEntity = new RegisterEntity();
                BeanUtils.copyProperties(registerationDto, registerEntity);
                loginRepository.updateProfile(registerEntity);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean saveEditedProfile(RegisterDto registerationDto, MultipartFile file) {
        RegisterDto existingDto = newsService.findByEmailInService(registerationDto.getEmail());
        RegisterEntity registerEntity = new RegisterEntity();

        if (file != null && !file.isEmpty() ) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
                registerationDto.setFileName(file.getOriginalFilename());
                registerationDto.setFileContentType(file.getContentType());
            } catch (IOException ignored) {

            }
        } else {
            registerationDto.setFileName(existingDto.getFileName());
            registerationDto.setFileContentType(existingDto.getFileContentType());
        }

        if(registerationDto!=null) {
            BeanUtils.copyProperties(registerationDto, registerEntity);
            return loginRepository.updateProfile(registerEntity);
        }

        return false;
    }


}
