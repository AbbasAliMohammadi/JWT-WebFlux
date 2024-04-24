package af.gov.reactiveAuthService.security.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;

@Component
public class JwtService {
    private static final String secret_key = "442A472D4B6150645267556B58703273357638792F423F4528482B4D62516554";
    private String expirationTime="28800";


    
	public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Mono<String> generateToken(Mono<UserDetails> user) {
        Map<String, Object> claims = new HashMap<>();
        // claims.put("role", user.getRoles());
        return doGenerateToken(claims, user);
    }

    private Mono<String> doGenerateToken(Map<String, Object> claims, Mono<UserDetails> user) {
        Long expirationTimeLong = Long.parseLong(expirationTime); //in second
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);
				Mono<String> token=user.flatMap(u->{
					return Mono.just(Jwts
							.builder()
							.setClaims(claims)
							.setSubject(u.getUsername())
							// .claim("auth", user.getAuthorities())
							.setIssuedAt(new Date(System.currentTimeMillis()))
							.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
							.signWith(getSignInKey())
							.compact());
				});
	
				return token;
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

	private Key getSignInKey() {
		byte[] keyBytes= Decoders.BASE64.decode(secret_key);
		return Keys.hmacShaKeyFor(keyBytes);
	}
    
    
}
