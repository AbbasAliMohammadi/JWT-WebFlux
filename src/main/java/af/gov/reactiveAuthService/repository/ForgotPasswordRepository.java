
package af.gov.reactiveAuthService.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import af.gov.reactiveAuthService.domain.auth.RefreshToken;

public interface ForgotPasswordRepository extends ReactiveCrudRepository<RefreshToken,Long>{

    
}
