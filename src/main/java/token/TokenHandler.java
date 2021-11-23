package token;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import login.User;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Calendar;

public class TokenHandler {
    private static Key key;
    private static final int TOKEN_EXPIRY = 1440; //1 day

    public static String generateJwtToken(User user) {
        Calendar expiry = Calendar.getInstance();
        expiry.add(Calendar.MINUTE, TOKEN_EXPIRY);
        return Jwts.builder()
                .setIssuer("YouQuiz")
                .claim("user", user)
                .signWith(SignatureAlgorithm.HS512, getKey())
                .setExpiration(expiry.getTime())
                .compact();
    }

    public static User validate(String token) throws NotAuthorizedException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getKey())
                    .parseClaimsJws(token)
                    .getBody();
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.convertValue(claims.get("user"), User.class);
        } catch (JwtException e) {
            System.out.println();
            throw new NotAuthorizedException(e.getMessage());
        }
    }


    private static Key getKey() {
//Generate a secret key, if there is none specified in the environment - only use fixed key in development for debugging
        if (key == null) {
            if (System.getenv("JWT_SECRET_KEY") != null && System.getenv("JWT_SECRET_KEY") != "") {
                String string = System.getenv("JWT_SECRET_KEY");
                key = new SecretKeySpec(string.getBytes(), 0, string.length(), "HS512");
            } else {
                key = MacProvider.generateKey(SignatureAlgorithm.HS512);
            }
        }
        return key;
    }
}
