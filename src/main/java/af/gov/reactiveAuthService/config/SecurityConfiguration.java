package af.gov.reactiveAuthService.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ReactiveAuthenticationManager;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Configuration 
@RequiredArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity 
public class SecurityConfiguration {

	// private final AuthenticationManager authenticationManager;
	private final SecurityContextRepository securityContextRepository;
	private final ReactiveAuthenticationManager authenticationManager;
	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception{ 
		http
		.csrf(csrf->csrf.disable())
		.authorizeExchange(auth->{
			auth
			.pathMatchers("api/auth/**").permitAll()
			.anyExchange().authenticated();
		})
		.exceptionHandling(exception->{
			exception
			.authenticationEntryPoint((response,error)->Mono.fromRunnable(()->{
				response.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			}))
			.accessDeniedHandler((response,error)->Mono.fromRunnable(()->{
				response.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
			}));
		}
		)
		.authenticationManager(authenticationManager)
		.securityContextRepository(securityContextRepository);
		
		return http.build();
	}


}