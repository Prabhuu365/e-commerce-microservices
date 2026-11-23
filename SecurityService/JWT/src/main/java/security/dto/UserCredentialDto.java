package security.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import security.entity.UserCredentials;

import java.util.Collection;
import java.util.List;

public class UserCredentialDto implements UserDetails {
    private String username;
    private String password;

    public UserCredentialDto(UserCredentials userCredentials) {
        this.username = userCredentials.getUsername();
        this.password = userCredentials.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
