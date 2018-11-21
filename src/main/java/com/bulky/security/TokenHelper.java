/**
 * 
 */
package com.bulky.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author kaala
 *
 */
@Component
public class TokenHelper {
	
	private JwtConfig jwtConfig;

	public TokenHelper(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}

	public String getUserKind(HttpServletRequest request) {		
		
		try {	// exceptions might be thrown in creating the claims if for example the token is expired			
		
			Claims claims = getClaims(request);
			if (claims==null) {
				return "INVALID_USER_KIND";
			}			
			Object value = claims.get("authorities");
			if (value instanceof List<?>) {
				List<?> values = (List<?>)value;
				if (values.size()>0) {
					return (String)values.get(0);
				}				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		//account
	}
	
	public Integer getIdAccount(HttpServletRequest request) {		
		
		try {	// exceptions might be thrown in creating the claims if for example the token is expired			
		
			Claims claims = getClaims(request);
			if (claims==null) {
				return null;
			}			
			Object value = claims.get("account");
			if (value instanceof Number) {
				 return ((Number)value).intValue();								
			}
		}catch (Exception e) {
			// 
		}
		return null;
	}
	
	
	public Claims getClaims(HttpServletRequest request) {
		String header = request.getHeader(jwtConfig.getHeader());
		
		// 2. validate the header and check the prefix
		if(header == null || !header.startsWith(jwtConfig.getPrefix())) {			
			return null;
		}		
		
		// 3. Get the token
		String token = header.replace(jwtConfig.getPrefix(), "");
		
		try {	// exceptions might be thrown in creating the claims if for example the token is expired
			
			// 4. Validate the token
			Claims claims = Jwts.parser()
					.setSigningKey(jwtConfig.getSecret().getBytes())
					.parseClaimsJws(token)
					.getBody();
			return claims;			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		//account
	}
	
	

}
