package es.gestor_bancos.gestorbancos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.gestor_bancos.gestorbancos.modelo.entidades.Banco;
import es.gestor_bancos.gestorbancos.modelo.entidades.Cuenta;
import es.gestor_bancos.gestorbancos.modelo.entidades.Usuario;
import es.gestor_bancos.gestorbancos.modelo.servicio.IGestorDB;
import jakarta.annotation.PostConstruct;

@Controller
@SessionAttributes("user")
public class CuentasController {

    @Autowired
    private IGestorDB gestorDB;

    private Usuario usuario;

    private Usuario receptor;

    private int idReceptor;

    @PostConstruct
    public void init() {

        usuario = new Usuario();

        receptor = new Usuario();

    }



/* * * * * * * * * * * *
 * * * * * * * * * * * *
 * *                 * *
 * *   CARGAR VISTAS * *
 * *                 * *
 * * * * * * * * * * * *
 * * * * * * * * * * * */
    
    // Carga la vista en la que se mostrarán las cuentas de un usuario
    @GetMapping("/cuentas")
    public String listarCuentasUsuario(@ModelAttribute("user") Usuario user, Model modelo) {

        usuario = user;

        modelo.addAttribute("usuario", usuario);

        modelo.addAttribute("cuentas", gestorDB.listarCuentasDeUsuario(usuario));
        
        return "cuentas";

    }

    // Carga la vista desde la que se recogerán los datos para crear una nueva cuenta para el usuario
    @GetMapping("/cuentas/nueva")
    public String crearNuevaCuenta(Model modelo) {
    
        Cuenta cuenta = new Cuenta();

        List<Banco> bancos = gestorDB.listarBancos();
    
        modelo.addAttribute("cuenta", cuenta);

        modelo.addAttribute("bancos", bancos);
        
        return "crear_cuenta";
    
    }
    
    // Carga la vista desde la que se ingresará o se retirará dinero de una cuenta
    @GetMapping("/cuentas/cajero/{id}")
    public String modificarSaldo(@PathVariable("id") int id, Model modelo) {

        modelo.addAttribute("cuenta", gestorDB.buscarCuentaPorId(id));

        return "modificar_saldo";

    }

    // Carga la vista desde la que el usuario introducirá los datos para realizar el bizum
    @GetMapping("/cuentas/bizum/{id}")
    public String prepararBizum(@PathVariable("id") int id, Model modelo) {

        Cuenta cuenta = gestorDB.buscarCuentaPorId(id);

        modelo.addAttribute("cuenta", cuenta);
        
        return "hacer_bizum";
        
    }

    // Carga la vista desde la que el usuario confirmará si desea hacer el bizum
    @GetMapping("/cuentas/bizum/confirmar/{id}")
    public String confirmarBizum(@PathVariable("id") int id, @ModelAttribute("receptor") Usuario receptor, @ModelAttribute("cantidad") double cantidad, Model modelo) {

        Cuenta cuenta = gestorDB.buscarCuentaPorId(id);

        modelo.addAttribute("cuenta", cuenta);

        modelo.addAttribute("receptor", receptor);

        modelo.addAttribute("cantidad", cantidad);

        return "confirmar_bizum";

    }



/* * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * *
 * *                         * *
 * *   MANIPULACIÓN DE DATOS * *
 * *                         * *
 * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * */

    // Crea una nueva cuenta, la inserta en la base de datos y te devuelve a la pantalla de lista de cuentas en caso de que todo haya ido bien, en caso contrario te muestra un error
    @PostMapping("/cuentas")
    public String guardarCuenta(@ModelAttribute("cuenta") Cuenta cuenta, RedirectAttributes redirectAttributes) {

        if (cuenta.getBanco() == null) {

            redirectAttributes.addFlashAttribute("error", "Debes seleccionar un banco antes de guardar la cuenta.");

            return "redirect:/cuentas/nueva";

        } else {

            cuenta.setUsuario(usuario);

            gestorDB.insertarCuenta(cuenta);

        }

        return "redirect:/cuentas";

    }

    // Cambia la cuenta desde la que el usuario realizará y recibirá los bizum
    @PostMapping("/cuentas/bizum")
    public String cambiarCuentaBizum(@RequestParam("bizum") int idCuentaBizum) {

        Cuenta cuentaBizum = gestorDB.buscarCuentaPorId(idCuentaBizum);

        if (cuentaBizum != null) {

            usuario.setCuentaBizum(cuentaBizum);

            gestorDB.modificarUsuario(usuario);

        }

        return "redirect:/cuentas";

    }

    // Guarda la nueva cantidad de dinero de la cuenta en función del dinero introducido o retirado, en caso de intentar retirar más dinero del que hay en la cuenta muestra un mensaje de error
    @PostMapping("/cuentas/{id}")
    public String guardarSaldo(@PathVariable("id") int id, @ModelAttribute("cuenta") Cuenta cuenta, @RequestParam("action") String action, RedirectAttributes redirectAttributes) {

        Cuenta cuentaExistente = gestorDB.buscarCuentaPorId(id);

        cuentaExistente.setId(id);

        if ("ingresar".equals(action)) {

            cuentaExistente.setDinero(cuentaExistente.getDinero() + cuenta.getDinero());

        } else if ("retirar".equals(action)) {

            if (cuenta.getDinero() > cuentaExistente.getDinero()) {

                redirectAttributes.addFlashAttribute("error", "No puedes retirar más dinero del que tienes en tu cuenta.");

                return "redirect:/cuentas/cajero/" + id;

            } else {

                cuentaExistente.setDinero(cuentaExistente.getDinero() - cuenta.getDinero());

            }

        }

        gestorDB.modificarCuenta(cuentaExistente);

        return "redirect:/cuentas";

    }

    // Comprueba que los datos introducidos para realizar el bizum sean correctos, en caso de intentar enviar más dinero del que hay en la cuenta muestra un mensaje de error
    @PostMapping("/cuentas/bizum/{id}")
    public String comprobarBizum(@PathVariable("id") int id, @RequestParam("cantidad") double cantidad, @RequestParam("telefono") String telefono, RedirectAttributes redirectAttributes) {

        Cuenta cuenta = gestorDB.buscarCuentaPorId(id);

        if (cantidad > cuenta.getDinero()) {

            redirectAttributes.addFlashAttribute("error", "No puedes enviar más dinero del que tienes en tu cuenta.");

            return "redirect:/cuentas/bizum/" + id;

        } else {

            receptor = gestorDB.buscarUsuarioPorTelefono(telefono);

            if (receptor == null){

                redirectAttributes.addFlashAttribute("error", "No existe ningún usuario con ese número de teléfono.");

                return "redirect:/cuentas/bizum/" + id;

            } else if (receptor.getCuentaBizum() == null) {
                
                redirectAttributes.addFlashAttribute("error", "El usuario con ese número de teléfono no tiene ninguna cuenta vinculada para recibir Bizum.");

                return "redirect:/cuentas/bizum/" + id;

            } else {

                idReceptor = receptor.getId();

                redirectAttributes.addFlashAttribute("receptor", receptor);

                redirectAttributes.addFlashAttribute("cantidad", cantidad);

            }

        }

        return "redirect:/cuentas/bizum/confirmar/" + id;
        
    }

    // Hace el bizum
    @PostMapping("/cuentas/bizum/confirmar/{id}")
    public String realizarBizum(@PathVariable("id") int id, @ModelAttribute("cantidad") double cantidad, RedirectAttributes redirectAttributes) {

        Cuenta cuenta = gestorDB.buscarCuentaPorId(id);

        receptor.setId(idReceptor);

        cuenta.setDinero(cuenta.getDinero() - cantidad);

        receptor.getCuentaBizum().setDinero(receptor.getCuentaBizum().getDinero() + cantidad);

        gestorDB.modificarCuenta(receptor.getCuentaBizum());

        gestorDB.modificarCuenta(cuenta);

        return "redirect:/cuentas";
        
    }

    // Elimina una cuenta
    @GetMapping("/cuentas/{id}")
    public String eliminarCuenta(@PathVariable("id") int id) {

        gestorDB.eliminarCuenta(id);

        return "redirect:/cuentas";

    }

    // Vuelve a la pantalla de visualizar cuentas
    @GetMapping("/cuentas/volver")
    public String volverAListarCuentas() {

        return "redirect:/cuentas";

    }

    // Cierra la sesion y vuelve a la pantalla de iniciar sesion
    @GetMapping("/cuentas/logout")
    public String cerrarSesion() {

        return "redirect:/login";

    }

}