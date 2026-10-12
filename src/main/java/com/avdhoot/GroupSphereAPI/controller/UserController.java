package com.avdhoot.GroupSphereAPI.controller;

import com.avdhoot.GroupSphereAPI.dto.loginDto.LoginRequestDto;
import com.avdhoot.GroupSphereAPI.dto.loginDto.LoginResponseDto;
import com.avdhoot.GroupSphereAPI.dto.userDto.CreateUserDetailResponse;
import com.avdhoot.GroupSphereAPI.dto.userDto.CreateUserRequestDto;
import com.avdhoot.GroupSphereAPI.service.JwtService;
import com.avdhoot.GroupSphereAPI.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    /*
        Authentication Endpoints
     */

    // Register
    @PostMapping("/auth/register")
    public ResponseEntity<CreateUserDetailResponse> registerMember(
            @Valid @RequestBody CreateUserRequestDto requestDto
    ) {
        CreateUserDetailResponse response = userService.registerUser(requestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Login
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto){
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(
                requestDto.username(),
                requestDto.password()
        );
        Authentication authentication = authenticationManager.authenticate(authenticationRequest);

        String token = jwtService.generateToken(authentication);

        LoginResponseDto response = new LoginResponseDto(token);
        return ResponseEntity.ok(response);
    }

}
