package af.gov.reactiveAuthService.security.service;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import af.gov.reactiveAuthService.repository.UserRepository;
import af.gov.reactiveAuthService.security.DTO.AuthRequestDTO;
import af.gov.reactiveAuthService.security.DTO.AuthResponseDTO;
import af.gov.reactiveAuthService.security.DTO.RegisterRequestDTO;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import af.gov.reactiveAuthService.domain.auth.User;
@Service
@AllArgsConstructor
public class AuthenticationService { 
    private final UserRepository userRepository; 
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ReactiveAuthenticationManager authenticationManager;
    private static final String secret_key = "442A472D4B6150645267556B58703273357638792F423F4528482B4D62516554";
    public Mono<AuthResponseDTO> register(Mono<RegisterRequestDTO> request) {

        return request.flatMap(
                req->{
                    User userData = User.builder()
                    .name(req.getName())
                    .lastname(req.getLastname())
                    .email(req.getEmail())
                    .password(passwordEncoder.encode(req.getPassword()))
                    .username(req.getUsername())
                    .activated(true)
                    .build();
                    Mono<UserDetails> res= userRepository.save(userData);
                    Mono<String> jwtToken = jwtService.generateToken(res);
                    return jwtToken.flatMap(token->{
                        return Mono.just( AuthResponseDTO.builder().token(token).build());
                    });
                }
        );
	    
	}
        public Mono<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO ar) {
              return userRepository.findByUsername(ar.getUsername())
            .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
            .flatMap(userDetails ->{
				return jwtService.generateToken(userRepository.findByUsername(userDetails.getUsername())).flatMap(token->{
					return Mono.just( AuthResponseDTO.builder().token(token).build());
				  });
			});
            
        }

    }
        

        


