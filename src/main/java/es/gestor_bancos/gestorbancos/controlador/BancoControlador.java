package es.gestor_bancos.gestorbancos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.gestor_bancos.gestorbancos.modelo.servicio.IGestorDB;

@Controller
public class BancoControlador {

    @Autowired
    private IGestorDB gestorDB;

    
}
