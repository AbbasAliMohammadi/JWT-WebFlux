package af.gov.reactiveAuthService.web;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import af.gov.reactiveAuthService.security.DTO.AuthRequestDTO;
import af.gov.reactiveAuthService.security.DTO.AuthResponseDTO;
import af.gov.reactiveAuthService.security.DTO.RegisterRequestDTO;
import af.gov.reactiveAuthService.security.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("api/auth/")
@AllArgsConstructor
public class AuthenticationResource {
	private final AuthenticationService authenticationService;

	@PostMapping("register")
	public Mono<AuthResponseDTO> register(@Valid @RequestBody Mono<RegisterRequestDTO> request) {	
		return authenticationService.register(request);

	}
	@PostMapping("/authenticate")
	public Mono<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO ar) {
	    return authenticationService.authenticate(ar);
		
    }



}
