package co.inventorsoft.academy.demorest.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {
    @GetMapping("/form")
    public String form(CsrfToken csrfToken, Model model) {
        model.addAttribute("_csrf", csrfToken);
        return "form";
    }
}