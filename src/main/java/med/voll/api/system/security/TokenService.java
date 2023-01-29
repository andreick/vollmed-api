package med.voll.api.system.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    private final String issuer = "Voll.med API";

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateJwt(Usuario usuario) {
        String subject = usuario.getId().toString();
        var expirationTime = Instant.now().plus(2, ChronoUnit.HOURS);
        var algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withExpiresAt(expirationTime)
                .sign(algorithm);
    }

    public Long getUsuarioId(String token) {
        var algorithm = Algorithm.HMAC256(secret);
        var decodedJWT = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);
        return Long.parseLong(decodedJWT.getSubject());
    }
}
