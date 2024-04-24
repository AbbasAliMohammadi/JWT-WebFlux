package af.gov.reactiveAuthService.domain.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "tbl_user")
public class User implements UserDetails {

    @Id
    private Long id;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String lastname;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private Boolean activated;

    // private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // List<GrantedAuthority> authorities = new ArrayList<>();
        // this.getRoles().forEach(role -> {
        //     authorities.add(new SimpleGrantedAuthority(role.getRole()));
        // });
        
        // return authorities;
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
       return true;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
       return this.username;
    }
    
}
