package app.controller;

import app.entity.api.login.AuthLoginRq;
import app.entity.api.register.AuthRegRq;
import app.entity.api.login.AuthLoginRs;
import app.entity.api.register.AuthRegRs;
import app.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("reg")
    public AuthRegRs register(@RequestBody AuthRegRq rq) {
        return service.register(rq.getUsername(), rq.getPassword()) ?
                AuthLoginRs.Ok() :
                new AuthRegRs(-1, "something went wrong");

    }


    @PostMapping("login")
    public AuthLoginRs autorize(@RequestBody AuthLoginRq rq) {
        return service.login(rq.getUsername(), rq.getPassword(),rq.getRememberMe())
                .map(t -> new AuthLoginRs(0, t))
                .orElse(new AuthLoginRs(-1, ""));
    }
}
