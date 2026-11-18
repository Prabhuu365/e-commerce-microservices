package security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.dto.UserRegisterDTO;
import security.exception.UsernameAlreadyExist;
import security.service.AuthService;

@RestController
@RequestMapping("/authenticate")
public class SecurityController {
    @Autowired
    private  AuthService authService;

    @PostMapping("/register")
    public String registeruser(@RequestBody UserRegisterDTO userRegisterDTO) throws UsernameAlreadyExist {
       return authService.registeruser(userRegisterDTO);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody UserRegisterDTO userRegisterDTO) throws UsernameNotFoundException {
       return authService.geToken(userRegisterDTO);
    }
}
