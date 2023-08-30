package com.example.pidev.Service.Classe;

import com.example.pidev.DAO.Entities.RestPasswordToken;
import com.example.pidev.DAO.Entities.User;
import com.example.pidev.DAO.Repositories.RestPasswordRepository;
import com.example.pidev.DAO.Repositories.UserRepositories;
import com.example.pidev.Service.Interface.IUser;
import com.example.pidev.email.EmailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements IUser {

    @Autowired
    RestPasswordRepository passwordTokenRepository;
    @Autowired
    private final PasswordTokenService passwordTokenService;
    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired

    private final PasswordEncoder passwordEncoder;
    @Override
    public void add(User a) {

        a.setPassword(passwordEncoder.encode(a.getPassword()));

        userRepositories.save(a);
    }



    @Override
    public User edit(User a) {
        return userRepositories.save(a);
    }

    @Override
    public List<User> selectAll() {
        return userRepositories.findAll();
    }


    @Override
    public User SelectById(int id) {
        if(userRepositories.findById(id).isPresent())
            return userRepositories.findById(id).get();
        return null;
    }


    @Override
    public void deleteById(int id) {
        userRepositories.deleteById(id);

    }
    @Override
    public boolean checkEmailExists(String email) {
        List<User> users= userRepositories.findAll();
        for(User d:users )
            if(d.getMail().equals(email))
                return true;
        return false;
    }


    @Override
    public User getUserByEmail(String email) {
        return userRepositories.findByMail(email);
    }

    public void sendPassMail(String userEmail) throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {

        String toAddress = userEmail;
        String fromAddress = "mailtest123yussef@gmail.com";
        String senderName = "RE-XPERT";
        String subject = "Your RE-Xpert password";
        String content = "Dear Mr/Madame, did you want to reset your password ? "
                + "Someone (hopefully you) has asked us to reset the password for your<br>"
                + "account. Please click the button below to do so. If you didn't<br>"
                + "request this password reset, you can go ahead and ignore this email!"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">RESET PASSWORD</a></h3>"
                + "Thank you,<br>"
                + "RE-XPERT.";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        String token = UUID.randomUUID().toString();

        String verifyURL = "http://localhost:8080/oauth/resetPassword/" + token;

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);


        mailSender.send(message);

        User user = userRepositories.findByMail(userEmail);


        user.setPasswordToken(token);
        userRepositories.save(user);


    }

    private void sendVerificationEmail(User user) throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {

        String toAddress = user.getMail();
        String fromAddress = "mailtest123yussef@gmail.com";
        String senderName = "RE-Xpert";
        String subject = "Please verify your registration";
        String content = "Dear Mr/Madame,<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "RE-Xpert.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        String verifyURL = "http://localhost:8085/oauth/verify/" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

        System.out.println("Email has been sent");
    }
    public boolean existsEmail(String mail) {

        return userRepositories.existsByMail(mail);
    }

    public ResponseEntity<User> verify(String verificationCode) {
        if (verificationCode == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = userRepositories.findByVerificationCode(verificationCode);

        if (user.isPresent()) {
            user.get().setVerificationCode(null);
            user.get().setEnabled(true);
            userRepositories.save(user.get());
            return ResponseEntity.ok(user.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public void verifyPassToken(String token,String newPassword) {

        Optional<User> user = userRepositories.findByPasswordToken(token);


        String encodedPassword = passwordEncoder.encode(newPassword);
        user.get().setPassword(encodedPassword);

        userRepositories.save(user.get());

    }

}
