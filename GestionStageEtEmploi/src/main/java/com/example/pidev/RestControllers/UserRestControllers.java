package com.example.pidev.RestControllers;

import com.example.pidev.DAO.Entities.User;
import com.example.pidev.Service.Classe.UserService;
import com.example.pidev.Service.Interface.ISecurity;
import com.example.pidev.Service.Interface.IUser;
import com.example.pidev.userdto.UsersDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserRestControllers {
    @Autowired
    private ModelMapper modelMapper;
    private IUser iUser;
    private final UserService userService;
    public final static String FOUND = "FOUND";
    public final static String BAD_REQUEST = "BAD_REQUEST";
    public final static String NOT_FOUND = "NOT_FOUND";

    private ISecurity iSecurity;

    @PostMapping("/user/add")
    public boolean ajouter (@RequestBody User user)
    {
        if(userService.checkEmailExists(user.getMail()))
           return false;

        iUser.add(user);
        return  true;
    }


    @PutMapping("/user/update")
    public User update(@RequestBody User user){
        System.out.println("user modif: " + user.getUsername());

        return userService.edit(user);



    }
    @PutMapping("/user/updateuser/{id}")
    public void updateuserbyID(@PathVariable String id,@RequestBody User user)
    {
        User u=iUser.getUserByEmail(id);
        u.setUsername(user.getUsername());


        u.setMail(user.getMail());

        iUser.edit(u);
    }
    @GetMapping("/admin/afficherUserbyID/{id}")
    public User AfficherByID(@PathVariable int id)
    {
        return iUser.SelectById(id);
    }

    @GetMapping("/admin/all")
    public List<User> AfficherUsers()
    {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return iUser.selectAll();
    }
    @DeleteMapping("/admin/delete/{id}")
    public void delete(@PathVariable int id)
    {
        iUser.deleteById(id);
    }
    @GetMapping("/session")
    public User getUSerCnnetcte()
    {
        return iUser.getUserByEmail( iSecurity.getCurrentUserName());
    }

    @PostMapping("/oauth/forgetPassword")

    public ResponseEntity<Object> forgetPassword(@RequestBody UsersDTO usersDTO) throws UnsupportedEncodingException, MessagingException {

        User userReq = modelMapper.map(usersDTO, User.class);
        userService.sendPassMail(userReq.getMail());

        return new ResponseEntity<>(FOUND, HttpStatus.OK);
    }

    @PostMapping("/oauth/resetPassword/{token}")

    public ResponseEntity<Object> resetPass(@PathVariable String token, @RequestBody UsersDTO usersDTO) {
        User userReq = modelMapper.map(usersDTO, User.class);
        System.out.println(token+ "  "+ userReq.getPassword());
        userService.verifyPassToken(token,userReq.getPassword());
        return new ResponseEntity<>(FOUND, HttpStatus.OK);
    }
    @GetMapping("/user/email/{email}")
    public boolean checkEmailExists(@PathVariable String email) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return userService.checkEmailExists(email);
    }



}
