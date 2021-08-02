package com.airbus.product.catelog.demo.service;

import java.util.Date;


import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultJwtBuilder;


@Service
public class JWTTokenService {

	@Value("${jwt.secret}")
	public String secretKey;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(JWTTokenService.class);
	
	public String jwtTokenGenerator(String id) {
		
		long timeMillis=System.currentTimeMillis()+ 300000;
		Date date = new Date(timeMillis);
		JwtBuilder builder =Jwts.builder().setId(id).setIssuedAt(new Date()).setExpiration(date).setSubject("usertoken").signWith(SignatureAlgorithm.HS256, secretKey);
		return builder.compact();
	}
	
	public Claims parseJwt(String jwt) {
		
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(jwt)
				.getBody();

		LOGGER.info("The details of claims: ");
		LOGGER.info("ID: " + claims.getId());
		LOGGER.info("Subject: " + claims.getSubject());
		LOGGER.info("Exp time: " + claims.getExpiration());
		LOGGER.info("issue at: " + claims.getIssuedAt());
		return claims;
		
		
	}
	
	public Boolean isTokenExpired(String token) {
		final Date expiration = parseJwt(token).getExpiration();
		return expiration.before(new Date());
	}


}
