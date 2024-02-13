package es.gestor_bancos.gestorbancos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.gestor_bancos.gestorbancos.modelo.servicio.IGestorDB;

@Controller
public class CuentaControlador {

    @Autowired
    private IGestorDB gestorDB;
    
    @GetMapping("/cuentas")
    public String listarCuentas(Model modelo) {

        modelo.addAttribute("cuentas", gestorDB.listarCuentas());
        return "cuentas";
    }
    
}
