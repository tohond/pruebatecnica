package omarp.pruebatecnica.controller;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omarp.pruebatecnica.dto.LoginRequest;
import omarp.pruebatecnica.dto.LoginResponse;
import omarp.pruebatecnica.service.JwtService;

@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;


	
	    @PostMapping("/createSocio")
	    public String createSocio(@Valid @RequestBody Map<String, String> login) {
	        
	        return "Socio creado";
	    }
	    
	    

	    @PostMapping("/login")
	    public LoginResponse login(@Valid @RequestBody LoginRequest login) {
	       
	        Authentication auth = authenticationManager
	                .authenticate(
	                        new UsernamePasswordAuthenticationToken(
	                                login.getUsername(),
	                                login.getPassword()
	                        )
	                );
	    
	        String token = jwtService.generateToken((UserDetails) auth.getPrincipal());
	     
	        return new LoginResponse(token);
	    }
}
