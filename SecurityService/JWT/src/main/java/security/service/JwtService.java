package security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.Decoder;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtService {
    private  final String secret = "guywefyg728937892yhuhef9283hf792gy32892yf8h9f2fh";

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
