package es.gestor_bancos.gestorbancos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.gestor_bancos.gestorbancos.modelo.Encriptador;
import es.gestor_bancos.gestorbancos.modelo.entidades.Usuario;
import es.gestor_bancos.gestorbancos.modelo.servicio.IGestorDB;

@Controller
@SessionAttributes("user")
public class UsuariosController {

    @Autowired
    private IGestorDB gestorDB;

/* * * * * * * * * * * *
 * * * * * * * * * * * *
 * *                 * *
 * *   CARGAR VISTAS * *
 * *                 * *
 * * * * * * * * * * * *
 * * * * * * * * * * * */

    // Carga la vista de la pantalla para iniciar sesión
    @GetMapping({"/login", "/"})
    public String mostrarLogin(Model modelo) {

        modelo.addAttribute("usuario", new Usuario());

        return "login";

    }

    // Carga la vista de la pantalla para registrarse
    @GetMapping("/register")
    public String mostrarRegistro(Model modelo) {

        modelo.addAttribute("usuario", new Usuario());

        return "register";

    }



/* * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * *
 * *                         * *
 * *   MANIPULACIÓN DE DATOS * *
 * *                         * *
 * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * */

    // Comprueba que el correo y la contraseña usados para el inicio de sesión son correctas, en caso de no ser correctas muestra un mensaje de error
    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes, Model modelo) {
    
        Usuario usuarioExistente = gestorDB.buscarUsuarioPorEmail(usuario.getEmail());

        Encriptador encriptador = new Encriptador();
    
        if (usuarioExistente != null && usuarioExistente.getPassword().equals(encriptador.encriptar(usuario.getPassword()))) {

            modelo.addAttribute("user", usuarioExistente);
            
            return "redirect:/cuentas";

        } else {

            redirectAttributes.addFlashAttribute("error", "El correo o la contraseña incorrectos.");

            return "redirect:/login";

        }

    }

    // Comprueba que todos los campos hayan sido rellenados correctamente e inserta un nuevo usuario, en caso contrario muestra un mensaje de error
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

    // Vuelve a la pantalla de iniciar sesion
    @GetMapping("/register/volver")
    public String volverAIniciarSesion() {

        return "redirect:/login";

    }

}