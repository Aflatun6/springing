package five.app.handler;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class PersonNotFoundException {
    @org.springframework.web.bind.annotation.ExceptionHandler({RuntimeException.class})
//    @ResponseStatus(value = HttpStatus.NOT_FOUND,
//            reason = "Requested person was not found")
    public ModelAndView exceptionHandler(HttpServletRequest req, RuntimeException ex) {
        ModelAndView m = new ModelAndView();
        m.addObject("name", ex.getClass().getSimpleName());
        m.addObject("url", req.getRequestURL());
        m.addObject("ex", ex);
        m.setViewName("five/exception");
        return m;
    }
}
