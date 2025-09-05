package ra.edu.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expired}")
    private long expired;
    // tạo tọken
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expired))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSecretKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
    // validatetoken
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parse(token);
            return true;
        }catch (MalformedJwtException e){
            log.error("Invalid jwt token ", e.getMessage());
        }catch (ExpiredJwtException e){
            log.error("Expired jwt token ", e.getMessage());
        }catch (UnsupportedJwtException e){
            log.error("Unsupported jwt token ", e.getMessage());
        }catch (IllegalArgumentException e){
            log.error("Jwt token has been rejected ", e.getMessage());
        }
        return false;
    }

    // giải mã token
    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
