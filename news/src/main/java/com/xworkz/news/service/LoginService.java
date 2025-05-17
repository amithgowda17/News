package com.xworkz.news.service;

import com.xworkz.news.dto.RegisterDto;
import org.springframework.web.multipart.MultipartFile;

public interface LoginService {

    boolean generateOtpInService(String email,String otp);

    boolean verifyOtp(String email,String otpEntered);

    boolean updatePasswordInService(String email,String password,String confirmPassword);

    boolean saveEditedProfile(RegisterDto registerationDto, MultipartFile file);
}
