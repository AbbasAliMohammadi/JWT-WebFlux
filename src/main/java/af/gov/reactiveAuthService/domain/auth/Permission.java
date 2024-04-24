package af.gov.reactiveAuthService.domain.auth;



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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_permission")
public class Permission {
    @Id
    private Long id;

    @NotNull
    private String permission;

}
