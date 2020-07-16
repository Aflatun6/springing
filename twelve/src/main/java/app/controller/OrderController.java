package app.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Log4j2
@RequestMapping("order")
public class OrderController {

    @GetMapping("new/1")
    public String handle1(Authentication ahth){
        Object principal = ahth.getPrincipal(); // this is our current logged user
        log.info(principal);

        return "OK1";
    }

    @GetMapping("new/2")
    public String handle2(Principal principal){ // it has more info about our user
        log.info(principal);
        return "OK2";
    }

}
