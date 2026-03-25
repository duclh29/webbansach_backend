package com.leduc.webbansach_backend.service;

import com.leduc.webbansach_backend.entity.NguoiDung;
import com.leduc.webbansach_backend.entity.Quyen;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
@Service
public class JwtService {

    public static final String SECRET = "my_super_secret_key_1234567890123456";

    @Autowired
    private UserService userService;

    // tao jwt dua tren dang nhap
    public String generateToken(String tenDangNhap) {
        Map<String, Object> claims = new HashMap<>();
        NguoiDung nguoidung = userService.findByUsername(tenDangNhap);

        boolean isAdmin = false;
        boolean isUser = false;
        boolean isStaff = false;

        if (nguoidung != null && nguoidung.getDanhSachQuyen().size() > 0) {
            List<Quyen> listQ = nguoidung.getDanhSachQuyen();
            for (Quyen quyen : listQ) {
                if (quyen.getTenQuyen().equals("ADMIN")) {
                    isAdmin = true;
                }
                if (quyen.getTenQuyen().equals("USER")) {
                    isUser = true;
                }
                if (quyen.getTenQuyen().equals("STAFF")) {
                    isStaff = true;
                }
            }
        }
        claims.put("isAdmin", isAdmin);
        claims.put("isUser", isUser);
        claims.put("isStaff", isStaff);
        return createToken(claims, tenDangNhap);

    }

    //tao jwt voi cac claims da chon
    private String createToken(Map<String, Object> claims, String tenDangNhap) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(tenDangNhap)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // jwt het han sau 30 phut
                .signWith(getSigneKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // lau sercet key
    private Key getSigneKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // trich xuat thong tin
    private Claims extractAllCliams(String token) {

        return Jwts.parser().setSigningKey(getSigneKey()).parseClaimsJws(token).getBody();

    }

    //trich xuat thong tin cho 1 claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllCliams(token);
        return claimsTFunction.apply(claims);

    }

    // kiem tra thoi gian het han jwt
    public Date extractExpiration(String token) {
        final Claims claims = extractAllCliams(token);
        return claims.getExpiration();

    }

    public String extractUsername(String token) {
        final Claims claims = extractAllCliams(token);
        return claims.getSubject();

    }

    //kiem tra jwt da het han
    public Boolean iSTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //kiem tra hop cua token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String tenDangNhap = extractUsername(token);
        return (tenDangNhap.equals(userDetails.getUsername()) && !iSTokenExpired(token));
    }

}
