package es.gestor_bancos.gestorbancos.Vista;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "¡Hola desde Thymeleaf!");
        return "index";
    }
    
}
