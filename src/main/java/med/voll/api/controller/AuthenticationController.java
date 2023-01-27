package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.AuthenticationDto;
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

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody @Valid AuthenticationDto dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = authManager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
