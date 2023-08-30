package com.example.pidev.RestControllers;

import com.example.pidev.Service.Classe.UserService;
import com.example.pidev.email.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class SendEmailRestController {
    private EmailService emailService;
    private UserService userService;


//    @GetMapping("/forgetpassword/{email}")
//    public String forgetpassword(@PathVariable("email") String email) {
//        return emailService.forgetpassword(email);
//    }

    @PostMapping("/SendEmailForgetpassword/{email}")
    public String test(@PathVariable String email) {

//        return "done";
        return emailService.forgetpassword(email);

    }
    @GetMapping("/email/reset/{token}/{email}/{password}")
    public int reset(@PathVariable("token") String token,@PathVariable("email") String email,@PathVariable("password") String password) {
        return emailService.resetPassword(token,email,password);
    }
}