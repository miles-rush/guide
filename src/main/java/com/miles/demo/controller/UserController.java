package com.miles.demo.controller;


import com.miles.demo.bean.ResponseCode;
import com.miles.demo.bean.User;
import com.miles.demo.repository.UserRepository;
import com.miles.demo.util.TokenUtil;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    static ResponseCode REGISTER_OK = new ResponseCode(1,"register success");
    static ResponseCode REGISTER_FAIL = new ResponseCode(0,"register fail");
    static ResponseCode REGISTER_FAIL_REPEAT = new ResponseCode(0,"register fail account repeat");
    @PostMapping(value = "/user/register")
    private ResponseCode register(@RequestParam("account") String account,@RequestParam("password") String password) {
        if (userRepository.findByUserAccount(account) != null) {
            return REGISTER_FAIL_REPEAT;
        }
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setAdmin(false);

        if (userRepository.save(user) != null) {
            return REGISTER_OK;
        }else {
            return REGISTER_FAIL;
        }
    }

//    static ResponseCode LOGIN_OK = new ResponseCode(1,"login success");
      static ResponseCode LOGIN_FAIL = new ResponseCode(0,"login fail");
//    static ResponseCode LOGIN_OK_ADMIN = new ResponseCode(2,"login success admin");
    @PostMapping(value = "/user/login")
    private ResponseCode login(@RequestParam("account") String account,@RequestParam("password") String password) {
        User user = userRepository.findByUserAccountAndPassword(account,password);
        if (user != null) {
            ResponseCode LOGIN_OK = new ResponseCode(1,"login success", user.getId());
            ResponseCode LOGIN_OK_ADMIN = new ResponseCode(2,"login success admin", user.getId());
            if (user.getAdmin()) {
                String token = TokenUtil.sign(user);
                LOGIN_OK_ADMIN.setAdditionalToken(token);
                return LOGIN_OK_ADMIN;
            }
            return LOGIN_OK;
        }
        return LOGIN_FAIL;
    }

    @GetMapping(value = "/user/query")
    private String getUserAccount(@RequestParam("id") Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return user.getAccount();
        }
        return "";
    }

    static ResponseCode EDIT_OK = new ResponseCode(1,"change password success");
    static ResponseCode EDIT_FAIL = new ResponseCode(0,"change password fail");
    static ResponseCode EDIT_FAIL_KEY = new ResponseCode(0,"password wrong fail");
    @PostMapping(value = "/user/editPassword")
    private ResponseCode editPassword(@RequestParam("id") Integer id,
                                      @RequestParam("oldPassword") String oldPassword,
                                      @RequestParam("newPassword") String newPassword) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.getPassword().equals(oldPassword)) {
                User newUser = new User();
                newUser.setId(id);
                newUser.setAccount(user.getAccount());
                newUser.setPassword(newPassword);
                newUser.setAdmin(user.getAdmin());
                if (userRepository.save(newUser) != null) {
                    return EDIT_OK;
                }else {
                    return EDIT_FAIL;
                }
            }
            return EDIT_FAIL_KEY;
        }
        return EDIT_FAIL;
    }
}
