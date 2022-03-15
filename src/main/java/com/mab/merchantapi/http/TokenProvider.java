package com.mab.merchantapi.http;

import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {
	
	private long tokenValidityInMilliseconds=1000*60*60*24;
	
	private Key key;
	
	@PostConstruct
	private void init() {
		byte[] keyBytes;
        String secret ="ZTY5ZjQ5YmEwNDQxMTk5NDAzOGFjNjQ2MDc3Y2Y1OTkwNGU2Y2I5MmI1NzllNmQ5YTkxYzcyZDhiOGRiYzhkZTk3NzVmOGFlMDZkNTdiYjU4MmE4YTg1ZjAxNDM1OTVkNTFhZjI0YWQ5NmM1MGExZDM1MzI2YTFmOGM3YzFlYjc=";
        keyBytes = Base64.getDecoder().decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String createToken(Authentication authentication) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);
        return Jwts.builder()
            .setSubject(authentication.getName())
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact();
    }
	
	public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();

        ArrayList<GrantedAuthority> list= new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
        
        Collection<? extends GrantedAuthority> authorities = list;

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) throws Exception {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        }
        catch (ExpiredJwtException e) {
        	throw e;
        }
        catch (JwtException | IllegalArgumentException e) {
            
        }
        return false;
    }
}
