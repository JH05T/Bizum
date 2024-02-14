package es.gestor_bancos.gestorbancos.modelo.servicio;

import java.util.List;
import java.util.Optional;

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

  public Cuenta buscarCuentaPorId(int id);
  
  public List<Cuenta> buscarCuentaPorBanco(Banco banco);

  public List<Cuenta> listarCuentasDeUsuario(Usuario usuario);

  public void insertarCuenta(Cuenta cuenta);

  public void modificarCuenta(Cuenta cuenta);

  public void eliminarCuenta(int id);

/* * * * * * * * * * * * *
 *                       *
 *   GESTIÓN DE BANCOS   *
 *                       *
 * * * * * * * * * * * * */

  public Optional<Banco> buscarBancoPorId(int id);

  public void insertarBanco(Banco banco);
  
  public List<Banco> listarBancos();
    
}