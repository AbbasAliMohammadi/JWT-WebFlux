package af.gov.reactiveAuthService.config;

import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;


import af.gov.reactiveAuthService.security.service.JwtService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
@Component
@AllArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository{
    private final ReactiveAuthenticationManager authenticationManager;
    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
        .filter(authHeader -> authHeader.startsWith("Bearer "))
        .flatMap(authHeader -> {
            String authToken = authHeader.substring(7);
            Authentication auth= new UsernamePasswordAuthenticationToken(authToken, authToken);
            return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
        });
    }
    
}
