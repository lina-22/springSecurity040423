package com.workshop.lina.controlers;

import com.workshop.lina.dto.AuthenticationResponse;
import com.workshop.lina.dto.RegisterRequest;
import com.workshop.lina.dto.AuthenticationRequest;
import com.workshop.lina.config.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/V1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        System.out.println("test :" + request.toString());
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        System.out.println("test :" + request.toString());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
