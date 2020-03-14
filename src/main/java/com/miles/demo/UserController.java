package com.miles.demo;


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
    private ResponseCode register(@RequestParam("account") String account,@RequestParam String password) {
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

    static ResponseCode LOGIN_OK = new ResponseCode(1,"login success");
    static ResponseCode LOGIN_FAIL = new ResponseCode(0,"login fail");
    static ResponseCode LOGIN_OK_ADMIN = new ResponseCode(2,"login success admin");
    @PostMapping(value = "/user/login")
    private ResponseCode login(@RequestParam("account") String account,@RequestParam String password) {
        User user = userRepository.findByUserAccountAndPassword(account,password);
        if (user != null) {
            if (user.getAdmin()) {
                return LOGIN_OK_ADMIN;
            }
            return LOGIN_OK;
        }
        return LOGIN_FAIL;
    }
}
