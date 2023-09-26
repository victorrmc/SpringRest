package es.cifpcm.SpringRestMarrero.auth;


import es.cifpcm.SpringRestMarrero.config.JwtService;
import es.cifpcm.SpringRestMarrero.dao.UsersRepository;
import es.cifpcm.SpringRestMarrero.modelo.Role;
import es.cifpcm.SpringRestMarrero.modelo.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UsersRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = Users.builder()
        .usuname(request.getUsuname())
            .usupass(passwordEncoder.encode(request.getUsupass()))
        .role(Role.USER)
        .build();
    repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsuname(),
            request.getUsupass()
        )
    );
    var user = repository.findByUsuname(request.getUsuname())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}
