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

  // Busca un usuario en la base de datos a partir del id
  public Usuario buscarUsuarioPorId(int id);

  // Busca un usuario en la base de datos a partir del telefono
  public Usuario buscarUsuarioPorTelefono(String telefono);

  // Busca un usuario en la base de datos a partir del email
  public Usuario buscarUsuarioPorEmail(String email);

  // Inserta un usuario en la base de datos
  public void insertarUsuario(Usuario usuario);

  // Modifica un usuario de la base de datos
  public void modificarUsuario(Usuario usuario);



/* * * * * * * * * * * * *
 *                       *
 *   GESTIÓN DE CUENTAS  *
 *                       *
 * * * * * * * * * * * * */

  // Busca una cuenta en la base de datos a partir del id
  public Cuenta buscarCuentaPorId(int id);
  
  // Busca las cuentas de un banco en la base de datos
  public List<Cuenta> buscarCuentaPorBanco(Banco banco);

  // Busca las cuentas de un usuario en la base de datos
  public List<Cuenta> listarCuentasDeUsuario(Usuario usuario);

  // Inserta una cuenta en la base de datos
  public void insertarCuenta(Cuenta cuenta);

  // Modifica una cuenta de la base de datos
  public void modificarCuenta(Cuenta cuenta);

  // Elimina una cuenta de la base de datos
  public void eliminarCuenta(int id);


  
/* * * * * * * * * * * * *
 *                       *
 *   GESTIÓN DE BANCOS   *
 *                       *
 * * * * * * * * * * * * */

  // Busca un banco en la base de datos a partir del id
  public Banco buscarBancoPorId(int id);
  
  // Busca los bancos en la base de datos
  public List<Banco> listarBancos();

  // Inserta un banco en la base de datos
  public void insertarBanco(Banco banco);
    
}