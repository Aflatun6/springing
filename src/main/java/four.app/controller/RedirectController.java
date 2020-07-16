package four.app.controller;

import four.app.entity.Book;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/re")
public class RedirectController {
    ////////////////////////////////////
    @GetMapping("book")
    public Book handle_book() {
        return new Book("a", "B");
    }
    //////////////////////////////////////////////
    ////////////////////////////////////////////////
    // REDIRECT ADD ATTRIBUTE  WILL BE SEEN BY EVERYONE
    @GetMapping("a")
    public RedirectView handle_a(RedirectAttributes r) {   //   redirect to  re/d
        r.addAttribute("name", "bbb"); // EVERYONE CAN SEE IT !!!!!!!!!!!!!!!!
        return new RedirectView("/re/d");
    }

    @GetMapping("d") // requests from   re/a
    public Book handle_d(@RequestParam String name) {
        return new Book("aaa", name);
    }

    ///////////////////////////////////////////////////
    ////////////////////////////////////////////////////
    // HTTP SESSION ATTRIBUTES CAN BE EXTRACTED ONLY FROM  HTTP SESSION
    @GetMapping("s")
    public RedirectView handle_s(HttpSession session) {  //   redirect to  re/v
        session.setAttribute("name", "ccc");
        return new RedirectView("v");  // same as => /re/book !!!!!!!!!!!
    }

    @GetMapping("v") // requests from   re/s
    public Book handle_v(HttpSession session) {
        Object nameObj = session.getAttribute("name");
        String name = (String) nameObj;
        return new Book("aaa", name);
    }

    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    //  REDIRECT ADD FLASH ATTRIBUTE   WORK   WITH    MODEL!!!!
    @GetMapping("z")
    public RedirectView handle_z(RedirectAttributes r) {
        r.addFlashAttribute("name", "alik");
        return new RedirectView("k");
    }

    @GetMapping("k")
    public Book handle_k(Model model) {
        String name = (String) model.getAttribute("name");
        return new Book("kk", name);
    }
    //////////
    @GetMapping("q")
    public RedirectView handle_q(RedirectAttributes r) {
        r.addFlashAttribute("name", "alikkkk");
        return new RedirectView("j");
    }

    @GetMapping("j")
    public Book handle_j(@ModelAttribute("name") String name) {
        return new Book("jj", name);
    }
    /////////////////////////////////////////////
    ////////////////////////////////////////////////

}
