
package af.gov.reactiveAuthService.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import af.gov.reactiveAuthService.domain.auth.Permission;

public interface PermissionRepository extends ReactiveCrudRepository<Permission,Long> {
    
}
