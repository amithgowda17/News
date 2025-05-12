package com.xworkz.news.controller;

import com.xworkz.news.dto.LoginDto;
import com.xworkz.news.dto.RegisterDto;
import com.xworkz.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class NewsController {

    @Autowired
    NewsService newsService;

    @GetMapping("indexPage")
    public String homePage() {
        return "index";
    }

    @PostMapping("/register")
    public String userRegistration(@Valid RegisterDto registerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> bindingResults = bindingResult.getAllErrors();
            return "register";
        }
        String successMsg = newsService.registerInService(registerDto);
        redirectAttributes.addFlashAttribute("msg", successMsg);
        return "redirect:/registerPage";
    }

    @GetMapping("registerPage")
    public String registerPage() {
        return "register";
    }

    @GetMapping("isEmailExists")
    public ResponseEntity<String> emailExists(@RequestParam String email) {
        if (email != null) {
            RegisterDto registerationDto = newsService.findByEmailInService(email);
            if (registerationDto != null) {
                return ResponseEntity.ok("email already exists");
            }
        }
        return ResponseEntity.ok("email_accepted");
    }

    @GetMapping("isPhNoExists")
    public ResponseEntity<String> phoneExists(@RequestParam String phNo) {
        if (phNo != null) {
            boolean byPhInService = newsService.findByPhInService(phNo);
            if (byPhInService) {
                return ResponseEntity.ok("phone number already exists");
            }
        }
        return ResponseEntity.ok("phone number not exists");
    }


    @PostMapping("login")
    public String login(@Valid LoginDto loginDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        RegisterDto registerationDto = newsService.findByEmailInService(loginDto.getEmail());
        if (bindingResult.hasErrors()) {
            model.addAttribute("loginErrMsg", "Please enter valid data");
            return "login";
        } else {
            String message = newsService.loginDetails(loginDto);
            if (message.equals("invalid password")) {
                redirectAttributes.addFlashAttribute("loginErrMsg", message);
                redirectAttributes.addFlashAttribute("enteredEmail", loginDto.getEmail());
                return "redirect:/loginPage";
            } else {
                return "redirect:/user";
            }
        }
    }

    @GetMapping("loginPage")
    public String loginPage() {
        return "login";
    }


    @GetMapping("user")
    public String getUserPage(){
        return "userPage";
    }
}
