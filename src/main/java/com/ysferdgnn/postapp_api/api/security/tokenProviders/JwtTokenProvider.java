package com.ysferdgnn.postapp_api.api.security.tokenProviders;

import com.ysferdgnn.postapp_api.api.database.models.RefreshToken;
import com.ysferdgnn.postapp_api.api.database.services.RefreshTokenService;
import com.ysferdgnn.postapp_api.api.security.models.JwtUserDetails;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenProvider {

    @Value("${postapp.app.secret}")
    private String APP_SECRET;

    @Value("${postapp.expires.in}")
    private Long EXPIRES_IN;

    @Value("${postapp.refresh.token.expires.in}")
    private Long REFRESH_TOKEN_EXPIRES_IN;

    private RefreshTokenService refreshTokenService;

    public JwtTokenProvider(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    public String generateJwtToken(Authentication authentication){
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expireDate = new Date(new Date().getTime()+ EXPIRES_IN);

        return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET)
                .compact();

    }

    public String generateJwtTokenByUsersId(Long usersId){

        Date expireDate = new Date(new Date().getTime()+ EXPIRES_IN);

        return Jwts.builder().setSubject(Long.toString(usersId))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET)
                .compact();

    }

    public RefreshToken generateJwtTokenByUserId(Long userId){

        Optional<RefreshToken> refreshTokenDb = refreshTokenService.findByUserId(userId);

        RefreshToken refreshToken =  refreshTokenDb.orElse(new RefreshToken());
        Date expireDate = new Date(new Date().getTime()+ REFRESH_TOKEN_EXPIRES_IN);

        String token= Jwts.builder().setSubject(Long.toString(userId))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET)
                .compact();

         refreshToken.setToken(token);
         refreshToken.setUserId(userId);
         refreshToken.setExpiryDate(expireDate);


            refreshTokenService.saveOneRefreshToken(refreshToken);
         return refreshToken;
    }

   public  Long getUserIdFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
        return  Long.parseLong(claims.getSubject());
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            return !isTokenExpired(token);
        }
        catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e){
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
       Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
       return expiration.before(new Date());
    }
}
