package app.controller;

import app.entity.ConfirmationToken;
import app.entity.Person;
import app.repo.PersonRepo;
import app.repo.TokenRepo;
import app.service.EmailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonAccountController {


    private final PersonRepo PersonRepository;


    private final TokenRepo confirmationTokenRepository;


    private final EmailSenderService emailSenderService;

    public PersonAccountController(PersonRepo PersonRepository, TokenRepo confirmationTokenRepository, EmailSenderService emailSenderService) {
        this.PersonRepository = PersonRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailSenderService = emailSenderService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, Person Person) {
        modelAndView.addObject("Person", Person);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerPerson(ModelAndView modelAndView, Person Person) {

        Person existingPerson = PersonRepository.findByEmailIdIgnoreCase(Person.getEmailId());
        if (existingPerson != null) {
            modelAndView.addObject("message", "This email already exists!");
            modelAndView.setViewName("error");
        } else {
            PersonRepository.save(Person);

            ConfirmationToken confirmationToken = new ConfirmationToken(Person);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(Person.getEmailId());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("a.velibeyli6@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    + "http://localhost:8082/confirm-account?token=" + confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);

            modelAndView.addObject("emailId", Person.getEmailId());

            modelAndView.setViewName("successfulRegisteration");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmPersonAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            Person Person = PersonRepository.findByEmailIdIgnoreCase(token.getPerson().getEmailId());
            Person.setEnabled(true);
            PersonRepository.save(Person);
            modelAndView.setViewName("accountVerified");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

}