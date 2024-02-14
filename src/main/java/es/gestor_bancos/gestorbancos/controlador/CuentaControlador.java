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

import es.gestor_bancos.gestorbancos.modelo.entidades.Banco;
import es.gestor_bancos.gestorbancos.modelo.entidades.Cuenta;
import es.gestor_bancos.gestorbancos.modelo.entidades.Usuario;
import es.gestor_bancos.gestorbancos.modelo.servicio.IGestorDB;

@Controller
public class CuentaControlador {

    @Autowired
    private IGestorDB gestorDB;

    Usuario usuario = new Usuario();
    
    @GetMapping("/cuentas")
    public String listarCuentasUsuario(Model modelo) {

        usuario.setId(1);

        modelo.addAttribute("cuentas", gestorDB.listarCuentasDeUsuario(usuario));
        
        return "cuentas";

    }

    @GetMapping("/cuentas/nueva")
    public String crearNuevaCuenta(Model modelo) {
    
        Cuenta cuenta = new Cuenta();

        List<Banco> bancos = gestorDB.listarBancos();
    
        modelo.addAttribute("cuenta", cuenta);
        modelo.addAttribute("bancos", bancos);
        
        return "crear_cuenta";
    
    }

    @PostMapping("/cuentas")
    public String guardarCuenta(@ModelAttribute("cuenta") Cuenta cuenta) {

        usuario.setId(1);

        cuenta.setUsuario(usuario);

        gestorDB.insertarCuenta(cuenta);
        
        return "redirect:/cuentas";

    }

    @GetMapping("/cuentas/cajero/{id}")
    public String modificarSaldo(@PathVariable("id") int id, Model modelo) {

        modelo.addAttribute("cuenta", gestorDB.buscarCuentaPorId(id));

        return "modificar_saldo";

    }

    @PostMapping("/cuentas/{id}")
    public String guardarSaldo(@PathVariable("id") int id, @ModelAttribute("cuenta") Cuenta cuenta, @RequestParam("action") String action) {

        Cuenta cuentaExistente = gestorDB.buscarCuentaPorId(id);

        cuentaExistente.setId(id);

        if ("ingresar".equals(action)) {

            cuentaExistente.setDinero(cuentaExistente.getDinero() + cuenta.getDinero());

        } else if ("retirar".equals(action)) {

            if (cuentaExistente.getDinero() - cuenta.getDinero() < 0) {

                cuentaExistente.setDinero(0);

            } else {

                cuentaExistente.setDinero(cuentaExistente.getDinero() - cuenta.getDinero());

            }

        }

        gestorDB.modificarCuenta(cuentaExistente);

        return "redirect:/cuentas";

    }

    @GetMapping("/cuentas/{id}")
    public String eliminarCuenta(@PathVariable("id") int id) {

        gestorDB.eliminarCuenta(id);

        return "redirect:/cuentas";

    }
    
}
