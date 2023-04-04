package com.workshop.lina.controlers;

import com.workshop.lina.config.JwtUtils;
import com.workshop.lina.dao.UerDao;
import com.workshop.lina.dto.AuthenticationRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/V1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UerDao userDao;
    private final JwtUtils jwtUtils;

@PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request)


    {
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
       );

       final UserDetails user = userDao.findUserByEmail(request.getEmail());

       if (user != null){
           return ResponseEntity.ok(jwtUtils.generateToken(user));

       }
       return ResponseEntity.status(400).body("Some error has occurred");
    }
}
