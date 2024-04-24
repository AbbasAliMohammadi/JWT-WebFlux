
package af.gov.reactiveAuthService.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import af.gov.reactiveAuthService.domain.auth.Role;

public interface RoleRepository extends ReactiveCrudRepository<Role,Long> {

    
} 
