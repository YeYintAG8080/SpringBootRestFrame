package com.mab.merchantapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mab.merchantapi.http.TokenProvider;
import com.mab.merchantapi.model.ApiUser;
import com.mab.merchantapi.model.ApplicationUser;
import com.mab.merchantapi.model.ErrorResponse;
import com.mab.merchantapi.model.Login;
import com.mab.merchantapi.service.ApplicationUserService;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenProvider jWTTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ApplicationUserService applicationUserService;
	
	static class JWTToken {

        private String token;

        @JsonProperty("id_token")
        String getToken() {
            return token;
        }

        void setToken(String token) {
            this.token = token;
        }
    }
	
	@PostMapping(value="/authenticate", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> authorize(@Valid @RequestBody Login login) throws Exception {
		try {		
            Authentication authenticate = authenticationManager
                .authenticate(
                    new UsernamePasswordAuthenticationToken(
                    		login.getUser_id(), login.getPassword()
                    )
                );

            String token=jWTTokenProvider.createToken(authenticate);            
            JWTToken res=new JWTToken();
            res.setToken(token);
            
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            return new ResponseEntity<>(res, httpHeaders, HttpStatus.OK);
            
        } 
		catch(UsernameNotFoundException ex) {
			ErrorResponse err=new ErrorResponse();
        	err.setMessage("Account is not found");
        	err.setStatus(HttpStatus.NO_CONTENT.value());
        	return new ResponseEntity<>(err,HttpStatus.NO_CONTENT);
		}
		catch(DisabledException ex) {
			ErrorResponse err=new ErrorResponse();
        	err.setMessage("Your account is disabled. Please contact administrator.");
        	err.setStatus(HttpStatus.FORBIDDEN.value());
        	return new ResponseEntity<>(err,HttpStatus.FORBIDDEN);
		}
		catch (BadCredentialsException ex) {		
			ErrorResponse err=new ErrorResponse();
        	err.setMessage("Username or Password is wrong");
        	err.setStatus(HttpStatus.UNAUTHORIZED.value());
        	return new ResponseEntity<>(err,HttpStatus.UNAUTHORIZED);
        }
	}
	
	@Autowired 
	@Qualifier(value = "uat_namedJdbcTemplate")
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@PreAuthorize("@WebSecurity.checkPermissions(authentication,'GET_CUSTOMER_INFO')")
	@GetMapping("/user/{id}")
	public ResponseEntity<List<ApiUser>> getUser(@PathVariable("id") String userId) {
		String query = " SELECT * FROM API_USER WHERE userId = :userId";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		List<ApiUser> users = namedJdbcTemplate.query(query, params, BeanPropertyRowMapper.newInstance(ApiUser.class));
		return new ResponseEntity<List<ApiUser>>(users, HttpStatus.OK);
	}
}
