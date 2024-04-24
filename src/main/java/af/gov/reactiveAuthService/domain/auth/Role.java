package af.gov.reactiveAuthService.domain.auth;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl_Role")
public class Role {
    
    @Id
    private Long id;

    @NotNull
    private String role;

    private Set<Permission> permissions;
    
}
