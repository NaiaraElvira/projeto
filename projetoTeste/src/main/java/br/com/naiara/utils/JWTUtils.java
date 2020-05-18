//package br.com.naiara.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//public class JWTUtils {
//	private static String key = "SECRET_TOKEN";
//	public static final String TOKEN_HEADER = "Authorization";
//	
//	// Criar o token
//	public static String create(String subject) {
//		return Jwts.builder()
//				.setSubject(subject)
//				.signWith(SignatureAlgorithm.HS512, key)
//				.compact();
//	}
//
//	// Decodifica o token
//	public static Jws<Claims> decote(String token) {
//		return Jwts.parser().setSigningKey(key).parseClaimsJws(token);
//	}
//}
