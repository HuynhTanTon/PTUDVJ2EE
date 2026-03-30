package com.example.bai8.controller;

import com.example.bai8.service.AccountRegistrationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Profile("!test")
public class AuthController {

    private final AccountRegistrationService registrationService;

    public AuthController(AccountRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("form") RegisterForm form) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerSubmit(@Valid @ModelAttribute("form") RegisterForm form,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        try {
            registrationService.register(form.getUsername(), form.getPassword());
            return "redirect:/login?registered";
        } catch (IllegalArgumentException ex) {
            bindingResult.rejectValue("username", "duplicate", ex.getMessage());
            return "auth/register";
        }
    }

    /** Form đăng ký — bean validation. */
    public static class RegisterForm {
        @NotBlank(message = "Nhập username")
        @Size(min = 3, max = 50)
        private String username;

        @NotBlank(message = "Nhập mật khẩu")
        @Size(min = 4, max = 100)
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
