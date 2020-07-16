package springMvsFormPractice.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import springMvsFormPractice.app.entity.Employee;

import javax.validation.Valid;

@Controller
public class EmployeeController {
    @GetMapping
    public ModelAndView showForm(){
        return new ModelAndView("employeeHome.html","employee",new Employee());
    }

    @PostMapping
    public String submit(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, ModelMap model){
        if (result.hasErrors()) {
            return "springMvsFormPractice/error";
        }
        model.addAttribute("name", employee.getName());
        model.addAttribute("contactNumber", employee.getContactNumber());
        model.addAttribute("id", employee.getId());
        return "employeeView.html";
    }
}
