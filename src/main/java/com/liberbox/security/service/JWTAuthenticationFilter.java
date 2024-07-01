package com.liberbox.security.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liberbox.security.domain.UserSS;
import com.liberbox.security.domain.dto.ResponseDto;
import com.liberbox.security.domain.dto.UserLoginDto;
import com.liberbox.security.util.JWTUtil;
import com.liberbox.user.domain.User;
import com.liberbox.user.repository.UserRepository;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private UserRepository repository;
	private JWTUtil jwtUtil;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserRepository repository) {
		super();
		this.authenticationManager = authenticationManager;
		this.repository = repository;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserLoginDto creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginDto.class);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					creds.getEmail(), creds.getPassword(), new ArrayList<>());
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			return authentication;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		
		String username = ((UserSS) authResult.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		response.setHeader("access-control-expose-headers", "Authorization");
		response.setHeader("Authorization", "Bearer " + token);
		
		ResponseDto responseDto = toResponse(username, token);
	    response.setContentType("application/json");
	   
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.writeValue(response.getOutputStream(), responseDto);
	    
	}

	private ResponseDto toResponse(String username, String token) {
		Optional<User> user = repository.findByEmail(username);

		if (user.isPresent()) {
			return new ResponseDto(user.get().getId(), user.get().getEmail(), user.get().getNickname(),
					user.get().getPhone(), token);
		}

		throw new IllegalArgumentException(json().toString());
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(401);
		response.setContentType("application/json");
		response.getWriter().append(json());
	}

	private CharSequence json() {
		long date = new Date().getTime();
		return "{"
				+ "\"timestamp\": " + date + ", " 
				+ "\"status\": 401, "
				+ "\"error\": \"Não autorizado\", "
				+ "\"message\": \"Email ou senha inválidos\", "
				+ "\"path\": \"/login\"}";
	}
}
