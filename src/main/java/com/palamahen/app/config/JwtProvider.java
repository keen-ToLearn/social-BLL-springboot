package com.palamahen.app.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

	private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.valueOf("SECRET_KEY").label.getBytes());
	
	public static String generateToken(Authentication auth) {
		
		String jwt = Jwts.builder()
				.issuer("Palamahen")
				.issuedAt(new Date())
				.expiration(new Date(new Date().getTime() + 3600000))
				.claim("email", auth.getName())
				.signWith(key)
				.compact();
		
		return jwt;
	}
	
	public static String getEmailFromJwt(String jwt) {
		
		jwt = jwt.substring(jwt.indexOf(' ') + 1);
		
		Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
		
		String email = claims.get("email", String.class);
		
		return email;
	}
}
