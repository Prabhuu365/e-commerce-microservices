package security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.Decoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Map<String,Object> claim, String username) {
        String token = Jwts.builder().
                subject(username).
                claims(claim).
                issuedAt(new Date()).
                expiration(new Date(System.currentTimeMillis()+1000*60*60)).
                signWith(getsignkey(), SignatureAlgorithm.HS256).compact();

        return token;
    }

    private Key getsignkey() {
        byte[] key = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(key);
    }

    public String gettoken(String username) {
        Map<String,Object> claim =new HashMap<>();
        return createToken(claim,username);
    }
}
