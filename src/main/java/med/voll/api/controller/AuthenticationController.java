package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.AuthenticationDto;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.system.security.TokenDto;
import med.voll.api.system.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody @Valid AuthenticationDto dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = authManager.authenticate(authToken);
        var usuario = (Usuario) authentication.getPrincipal();
        String jwt = tokenService.generateJwt(usuario);
        return ResponseEntity.ok(new TokenDto(jwt));
    }
}
