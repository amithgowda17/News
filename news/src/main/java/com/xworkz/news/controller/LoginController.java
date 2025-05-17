package com.xworkz.news.controller;

import com.xworkz.news.dto.RegisterDto;
import com.xworkz.news.service.LoginService;
import com.xworkz.news.service.NewsService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/")
public class LoginController {

    private static String path = "D://Project//file_upload//";

    @Autowired
    NewsService newsService;

    @Autowired
    LoginService loginService;

    @PostMapping("otp")
    public String generateOtp(@RequestParam String email, String otp, Model model) {
        if (email != null) {
            RegisterDto registerationDto = newsService.findByEmailInService(email);
            boolean isSaved = loginService.generateOtpInService(email, otp);
            if (isSaved) {
                model.addAttribute("emailDto", registerationDto);
                model.addAttribute("sentMessage", "opt have been sent");
                return "emailOtp";
            }
            return "emailOtp";
        }
        return "emailOtp";

    }

    @PostMapping("verifyOtp")
    public String verifyOtp(@RequestParam String email, @RequestParam String optEntered, Model model) {
        if (email != null || optEntered != null) {
            RegisterDto registerationDto = newsService.findByEmailInService(email);
            if (registerationDto != null) {
                boolean isOtpVerified = loginService.verifyOtp(email, optEntered);
                if (isOtpVerified) {
                    model.addAttribute("dto", registerationDto);
                    return "updatePassword";
                }
                model.addAttribute("optVerification", "invalid otp");
                model.addAttribute("emailDto", registerationDto);
                return "emailOtp";
            }
        }
        return "emailOtp";
    }

    @PostMapping("updatePassword")
    public String updatePassed(@RequestParam String email, String password, String confirmPassword,Model model) {
        if (email != null && password != null && confirmPassword != null) {
            boolean isPasswordUpdated = loginService.updatePasswordInService(email, password, confirmPassword);
            if (isPasswordUpdated) {
                model.addAttribute("success","password reset successfull");
                return "login";
            }
        }
        return "login";
    }

    @GetMapping("news")
    public String getNewsPage(){
        return "news";
    }

    @GetMapping("profileUpdate")
    public String getUserProfilePage(@RequestParam String email,Model model){
        RegisterDto userRegisterDto = newsService.findByEmailInService(email);
        model.addAttribute("dto",userRegisterDto);
        return "userProfile";
    }

    @PostMapping("updateUserDetails")
    public String editRegisterDetails(@RequestParam("file") MultipartFile file, RegisterDto registerationDto1, Model model) {
        boolean updateMessage = loginService.saveEditedProfile(registerationDto1, file);
        RegisterDto registerationDto = newsService.findByEmailInService(registerationDto1.getEmail());
        if (updateMessage) {
            model.addAttribute("msg", "data updated successfully");
            model.addAttribute("details", registerationDto);
            return "userPage";
        } else {
            model.addAttribute("details", registerationDto);
            model.addAttribute("errMsg", "data not updated");
            return "userPage";
        }
    }

    @GetMapping("getImage/{fileName}")
    public void viewImage(@PathVariable String fileName, Model model, HttpServletResponse httpServletResponse) {
        File file = new File(path + fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream inputStream = new BufferedInputStream(fileInputStream);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            IOUtils.copy(inputStream, servletOutputStream);
            model.addAttribute("image",servletOutputStream);
            httpServletResponse.flushBuffer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
