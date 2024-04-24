package af.gov.reactiveAuthService.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import af.gov.reactiveAuthService.domain.auth.User;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User,Long> {

    Mono<UserDetails> findByUsername(String username);
    Mono<UserDetails> save(User user);

    
}
