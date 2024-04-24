package af.gov.reactiveAuthService.config;
import java.util.List;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import af.gov.reactiveAuthService.repository.UserRepository;
import af.gov.reactiveAuthService.security.service.JwtService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    
    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String username = jwtService.getUsernameFromToken(authToken);
        return Mono.just(jwtService.validateToken(authToken))
            .filter(valid -> valid)
            .switchIfEmpty(Mono.empty())
            .map(valid -> {
                Claims claims = jwtService.getAllClaimsFromToken(authToken);
                List<String> rolesMap = claims.get("role", List.class);
                return new UsernamePasswordAuthenticationToken(username, null,null);
                // return new UsernamePasswordAuthenticationToken(
                //     username,
                //     null,
                //     rolesMap.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                // );
            });
    }
}
