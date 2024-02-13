package es.gestor_bancos.gestorbancos.modelo.servicio;

import java.util.List;

import es.gestor_bancos.gestorbancos.modelo.entidades.Banco;
import es.gestor_bancos.gestorbancos.modelo.entidades.Cuenta;
import es.gestor_bancos.gestorbancos.modelo.entidades.Usuario;

public interface IGestorDB {

    /* * * * * * * * * * * * * *
     *                         *
     *   GESTIÓN DE USUARIOS   *
     *                         *
     * * * * * * * * * * * * * */

    public void insertarUsuario(Usuario usuario);


    /* * * * * * * * * * * * *
     *                       *
     *   GESTIÓN DE CUENTAS  *
     *                       *
     * * * * * * * * * * * * */

    public List<Cuenta> listarCuentas();

    public void insertarCuenta(Cuenta cuenta);
 
     /* * * * * * * * * * * * *
      *                       *
      *   GESTIÓN DE BANCOS   *
      *                       *
      * * * * * * * * * * * * */

    public void insertarBanco(Banco banco);
    
}