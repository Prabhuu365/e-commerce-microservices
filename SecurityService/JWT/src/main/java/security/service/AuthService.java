package security.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.dto.UserRegisterDTO;
import security.entity.UserCredentials;
import security.exception.UsernameAlreadyExist;
import security.repository.UserRepository;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    public String  registeruser(UserRegisterDTO userRegisterDTO) throws UsernameAlreadyExist {
       Optional<UserCredentials> user  = userRepository.findByUsername(userRegisterDTO.getUsername());
       if(user.isPresent()){
           throw new UsernameAlreadyExist("Username Already Exixst WIth Username"+userRegisterDTO.getUsername());
       }
       UserCredentials userCredentials = new UserCredentials();
       userCredentials.setUsername(userRegisterDTO.getUsername());
       userCredentials.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
       userRepository.save(userCredentials);
       return "User Registered Successfully";
    }

    public String geToken(UserRegisterDTO userRegisterDTO) throws UsernameNotFoundException{
       Optional<UserCredentials> user =  userRepository.findByUsername(userRegisterDTO.getUsername());
       if (!user.isPresent()) throw new UsernameNotFoundException("No user exists with username"+userRegisterDTO.getUsername());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRegisterDTO.getUsername(),userRegisterDTO.getPassword()));
        if(authentication.isAuthenticated()){
            String token = jwtService.gettoken(userRegisterDTO.getUsername());
        }

       return null;
    }
}
