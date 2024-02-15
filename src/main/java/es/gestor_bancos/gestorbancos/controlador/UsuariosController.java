package es.gestor_bancos.gestorbancos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.gestor_bancos.gestorbancos.modelo.entidades.Usuario;
import es.gestor_bancos.gestorbancos.modelo.servicio.IGestorDB;

@Controller
@SessionAttributes("user")
public class UsuariosController {

    @Autowired
    private IGestorDB gestorDB;

    @GetMapping({"/login", "/"})
    public String mostrarLogin(Model modelo) {

        modelo.addAttribute("usuario", new Usuario());

        return "login";

    }

    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes, Model modelo) {
    
        Usuario usuarioExistente = gestorDB.buscarUsuarioPorEmail(usuario.getEmail());
    
        if (usuarioExistente != null && usuarioExistente.getPassword().equals(usuario.getPassword())) {
            modelo.addAttribute("user", usuarioExistente); // Agregar el usuario al modelo y a la sesión
            return "redirect:/cuentas";
        } else {
            redirectAttributes.addFlashAttribute("error", "El correo o la contraseña incorrectos.");
            return "redirect:/login";
        }
    }


    @GetMapping("/register")
    public String mostrarRegistro(Model modelo) {

        modelo.addAttribute("usuario", new Usuario());

        return "register";

    }

    @PostMapping("/register")
    public String procesarRegistro(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes) {

        Usuario usuarioExistente = gestorDB.buscarUsuarioPorEmail(usuario.getEmail());

        if (usuarioExistente != null) {

            redirectAttributes.addFlashAttribute("error", "El email ya está en uso.");

            return "redirect:/register";

        }

        usuarioExistente = gestorDB.buscarUsuarioPorTelefono(usuario.getTelefono());

        if (usuarioExistente != null) {

            redirectAttributes.addFlashAttribute("error", "El teléfono ya está en uso.");

            return "redirect:/register";

        }

        if (usuario.getNombre().isEmpty() || usuario.getApellidos().isEmpty() || usuario.getTelefono().isEmpty() || usuario.getEmail().isEmpty() || usuario.getPassword().isEmpty()) {
            
            redirectAttributes.addFlashAttribute("error", "Todos los campos deben estar llenos.");
            
            return "redirect:/register";

        }

        gestorDB.insertarUsuario(usuario);

        return "redirect:/login";
    }

}
