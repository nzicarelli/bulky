/**
 * 
 */
package com.bulky.security;


import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bulky.account.UserData;
import com.bulky.support.AppUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author nando
 *
 */
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter   {
	
	// We use auth manager to validate the user credentials
	private AuthenticationManager authManager;
	
	private final JwtConfig jwtConfig;
    
	public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig) {
		this.authManager = authManager;
		this.jwtConfig = jwtConfig;
		
		// By default, UsernamePasswordAuthenticationFilter listens to "/login" path. 
		// In our case, we use "/auth". So, we need to override the defaults.
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			
			// 1. Get credentials from request
			UserCredentials creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
			
			// 2. Create auth object (contains credentials) which will be used by auth manager
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					creds.getUsername(), creds.getPassword(), Collections.emptyList());
			
			
			// 3. Authentication manager authenticate the user, and use UserDetialsServiceImpl::loadUserByUsername() method to load the user.
			return authManager.authenticate(authToken);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Upon successful authentication, generate a token.
	// The 'auth' passed to successfulAuthentication() is the current authenticated user.
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		Long now = System.currentTimeMillis();
		UserData uData = (UserData)auth.getPrincipal();
		String token = Jwts.builder()
			.setSubject(auth.getName())	
			// Convert to list of strings. 
			// This is important because it affects the way we get them back in the Gateway.
			.claim("authorities", auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
			.claim("account", uData.getIdAccount())
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
			.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
			.compact();
		
		// Add token to header
		response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
		response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		Map<String, Object> body = new HashMap<>();
		body.put("tk", token);
		body.put("expire", jwtConfig.getExpiration());
		
		body.put("user", uData);
		
		try {
			response.getWriter().println(AppUtil.toJson(body));
		} catch (Exception e) {
			throw new IOException(e);
		}
	
	}
	
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// AccountExpiredException, CredentialsExpiredException, DisabledException, LockedException
		SecurityContextHolder.clearContext();
		Map<String, Object> body = new HashMap<>();		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		if (failed instanceof LockedException) {			
			body.put("LOCKED", true);			
		}else if (failed instanceof AccountExpiredException) {
			body.put("EXPIRED", true);
		}else if (failed instanceof CredentialsExpiredException) {
			body.put("EXPIRED", true);
		}else if (failed instanceof DisabledException) {
			body.put("DISABLED", true);
		}else if (failed.getCause() instanceof NoResultException) {
			body.put("NOT_FOUND", true);
		}else {
			body.put("DISABLED", true);
		}		
		body.put("MSG", failed.getMessage());
		try {
			response.getWriter().println(AppUtil.toJson(body));
		} catch (Exception e) {
			throw new IOException(e);
		}
		
	}
	
	// A (temporary) class just to represent the user credentials
	private static class UserCredentials {
	    
		private String username = null;
	    private String password = null;
		
	    public String getUsername() {
			return username;
		}
		@SuppressWarnings("unused")
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		@SuppressWarnings("unused")
		public void setPassword(String password) {
			this.password = password;
		}    
	    
	}
}