package com.rss.feedhub.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.rss.feedhub.entity.AccountEntity;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author: ZhangKaijie
 * @Date: 2019/7/25 14:46
 * @Description:
 */
@Component
public class TokenUtil {
    private static final String base64SecretBytes = "T+HALoh2cztZWkl3yAK0bv7nueTsrZ9ypOKzm9ikwdI=";
    public String getToken(String username){
        //Token头算法和类型
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        //内容account和type
        Map<String, Object> claims = new HashMap<>();
        claims.put("username",username);
        claims.put("verification",Md5Util.inputPassToDbPass(username,base64SecretBytes));
//        claims.put("verification",Md5Util.inputPassToDbPass(account.getAccount(),"123456"));
        //签发时间
        Date iatdate = new Date();
        //到期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.DATE, 10);
        Date expiresDate = nowTime.getTime();
        String token="";
        token = Jwts.builder()
                .setClaims(claims)
                .setHeader(map)
                .setIssuedAt(iatdate)
                .setNotBefore(iatdate)
                .setExpiration(expiresDate)
                .signWith(SignatureAlgorithm.HS256, base64SecretBytes)
                .compact();
        return token;
    }

    public Claims parseJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(base64SecretBytes)
                .parseClaimsJws(token).getBody();
        return claims;
    }

    public void jwtVerifier(String token) {
        JWTVerifier verifyToken = JWT.require(Algorithm.HMAC256(Base64.getDecoder().decode(base64SecretBytes)))
                .build();
        verifyToken.verify(token);
    }

    public boolean tokenQualifier(String token, AccountEntity account) {
        String verification = (String) parseJwtToken(token).get("verification");
        if(verification.equals(Md5Util.inputPassToDbPass(account.getUserName(),base64SecretBytes)))
            return true;
        return false;
    }

    public boolean tokenDurationVerifier(String token){
        int Expiration = (Integer) parseJwtToken(token).get("exp");
        return Expiration > new Date().getTime()/1000;
    }

    public String getAdminToken(String admin){
        //Token头算法和类型
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        //内容account和type
        Map<String, Object> claims = new HashMap<>();
        claims.put("adminUser",admin);
        claims.put("verification",Md5Util.inputPassToDbPass(admin,base64SecretBytes));
        //签发时间
        Date iatdate = new Date();
        //到期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.DATE, 10);
        Date expiresDate = nowTime.getTime();
        String token="";
        token = Jwts.builder()
                .setClaims(claims)
                .setHeader(map)
                .setIssuedAt(iatdate)
                .setNotBefore(iatdate)
                .setExpiration(expiresDate)
                .signWith(SignatureAlgorithm.HS256, base64SecretBytes)
                .compact();
        return token;
    }
}
