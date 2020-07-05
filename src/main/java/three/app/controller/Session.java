package three.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/session")
public class Session {
    @GetMapping
    @ResponseBody
    public String getMapping(HttpSession session) {
        return String.format("sessionId:%s", session.getId());
    }

}
