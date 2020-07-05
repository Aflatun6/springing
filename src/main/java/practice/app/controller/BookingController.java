package practice.app.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;
import practice.app.entity.CurrentDetails;

@Log4j2
@Controller
@SessionAttributes(
        names = {CurrentDetails.ATTR},
        types = {CurrentDetails.class}
)
public class BookingController {

    private static String fmt(String s, Object... as) {
        return String.format(s, as);
    }

    @ModelAttribute(CurrentDetails.ATTR)
    public CurrentDetails create() {
        return new CurrentDetails();
    }


    @GetMapping("seat")
    public String handleSeat(Model m,
                             @ModelAttribute(CurrentDetails.ATTR) CurrentDetails cd) {
        m.addAttribute("seat", cd.getSeat());
//        log.info("GET -> /seat:");
//        log.info(cd);
        return "practiceapp/seat";
    }

    @PostMapping("seat")
    public RedirectView handleSeatPost(@ModelAttribute(CurrentDetails.ATTR) CurrentDetails cd) {
//        log.info(fmt("POST -> /name: %s", cd));
        return new RedirectView("name");
    }

    @GetMapping("name")
    public String handleName(Model m,
                             @ModelAttribute(CurrentDetails.ATTR) CurrentDetails cd) {
        m.addAttribute("name", cd.getName());
        m.addAttribute("surname", cd.getSurname());
//        log.info("GET -> /name:");
//        log.info(cd);
        return "practiceapp/name";
    }

    @PostMapping("name")
    public RedirectView handleNamePost( @ModelAttribute(CurrentDetails.ATTR) CurrentDetails cd) {
//        log.info(fmt("POST -> /name: %s", cd));
        return new RedirectView("card");
    }

    @GetMapping("card")
    public String handleCard(Model m,
                             @ModelAttribute(CurrentDetails.ATTR) CurrentDetails cd) {
        m.addAttribute("cardno", cd.getCardno());
//        log.info("GET -> /card:");
//        log.info(cd);
        return "practiceapp/card";
    }

    @PostMapping("card")
    public RedirectView handleCardPost( @ModelAttribute(CurrentDetails.ATTR) CurrentDetails cd) {
//        log.info(fmt("POST -> /card: %s", cd));

        return new RedirectView("confirm");
    }

    @GetMapping("confirm")
    public String handleConfirm(Model m,
                                @ModelAttribute(CurrentDetails.ATTR) CurrentDetails cd) {
        m.addAttribute("seat", cd.getSeat());
        m.addAttribute("name", cd.getName());
        m.addAttribute("surname", cd.getSurname());
        m.addAttribute("cardno", cd.getCardno());
        return "practiceapp/confirm";
    }


}
