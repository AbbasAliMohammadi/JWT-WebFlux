package af.gov.reactiveAuthService.domain.auth;

import java.time.LocalDate;

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
@Table(name="tbl_refreshtoken")
public class RefreshToken {
    
    @Id
    private Long id;

    @NotNull
    private String resetKey;

    @NotNull
    private LocalDate resetDate;
}
