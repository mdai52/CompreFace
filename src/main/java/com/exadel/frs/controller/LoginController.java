package com.exadel.frs.controller;

import com.exadel.frs.dto.AccessToken;
import com.exadel.frs.security.JwtTokenProvider;
import com.exadel.frs.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

  private final UserService userService;

  private final JwtTokenProvider jwtTokenProvider;

  @PostMapping("/")
  @ApiOperation(value = "Get jwt token")
  @ApiResponses({
      @ApiResponse(code = 401, message = "Authorisation not successful, access denied")
  })
  public ResponseEntity<AccessToken> loginUser(@ApiParam(value = "Username", required = true) String username
      , @ApiParam(value = "User password", required = true) String password) {
    String token = userService.login(username, password);
    return ResponseEntity.ok(new AccessToken(token));
  }
}