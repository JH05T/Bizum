package es.gestor_bancos.gestorbancos.modelo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gestor_bancos.gestorbancos.modelo.repositorios.BancoRepository;
import es.gestor_bancos.gestorbancos.modelo.repositorios.CuentaRepository;
import es.gestor_bancos.gestorbancos.modelo.repositorios.UsuarioRepository;
import es.gestor_bancos.gestorbancos.modelo.Encriptador;
import es.gestor_bancos.gestorbancos.modelo.entidades.Banco;
import es.gestor_bancos.gestorbancos.modelo.entidades.Cuenta;
import es.gestor_bancos.gestorbancos.modelo.entidades.Usuario;

import java.util.List;

@Service
public class GestorDB implements IGestorDB {

/* * * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * * * *
 * *                             * *
 * *   CONSTANTES Y VARIABLES    * *
 * *                             * *
 * * * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * * * */

    @Autowired
    private UsuarioRepository usuarios;
    
    @Autowired
    private CuentaRepository cuentas;
    
    @Autowired
    private BancoRepository bancos;


    
/* * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * *
 * *                       * *
 * *   MÉTODOS PÚBLICOS    * *
 * *                       * *
 * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * */



/* * * * * * * * * * * * * *
 *                         *
 *   GESTIÓN DE USUARIOS   *
 *                         *
 * * * * * * * * * * * * * */

    // Busca un usuario en la base de datos a partir del id
    @Override
    public Usuario buscarUsuarioPorId(int id) {

        return usuarios.buscarUsuarioPorId(id);

    }

    // Busca un usuario en la base de datos a partir del telefono
    @Override
    public Usuario buscarUsuarioPorTelefono(String telefono) {

        return usuarios.buscarUsuarioPorTelefono(telefono);

    }

    // Busca un usuario en la base de datos a partir del email
    @Override
    public Usuario buscarUsuarioPorEmail(String email) {

        return usuarios.buscarUsuarioPorEmail(email);

    }

    // Inserta un usuario en la base de datos
    @Override
    public void insertarUsuario(Usuario usuario) {

        Encriptador encriptador = new Encriptador();

        usuario.setPassword(encriptador.encriptar(usuario.getPassword()));

        usuarios.insertar(usuario);

    }

    // Modifica un usuario de la base de datos
    @Override
    public void modificarUsuario(Usuario usuario){

        usuarios.modificar(usuario);

    }


    
/* * * * * * * * * * * * *
 *                       *
 *   GESTIÓN DE CUENTAS  *
 *                       *
 * * * * * * * * * * * * */

    // Busca una cuenta en la base de datos a partir del id
    @Override
    public Cuenta buscarCuentaPorId(int id) {

        return cuentas.buscarCuentaPorId(id);

    }
  
    // Busca las cuentas de un banco en la base de datos
    @Override
    public List<Cuenta> buscarCuentaPorBanco(Banco banco) {

        return cuentas.buscarCuentaPorBanco(banco);

    }

    // Busca las cuentas de un usuario en la base de datos
    @Override
    public List<Cuenta> listarCuentasDeUsuario(Usuario usuario) {
        
        return cuentas.listarCuentasPorUsuario(usuario);

    }

    // Inserta una cuenta en la base de datos
    @Override
    public void insertarCuenta(Cuenta cuenta) {

        cuentas.insertar(cuenta);

    }

    // Modifica una cuenta de la base de datos
    @Override
    public void modificarCuenta(Cuenta cuenta) {

        cuentas.modificar(cuenta);
        
    }

    // Elimina una cuenta de la base de datos
    @Override
    public void eliminarCuenta(int id) {

        cuentas.eliminar(id);

    }



/* * * * * * * * * * * * *
 *                       *
 *   GESTIÓN DE BANCOS   *
 *                       *
 * * * * * * * * * * * * */

    // Busca un banco en la base de datos a partir del id
    @Override
    public Banco buscarBancoPorId(int id) {

        return bancos.buscarBancoPorId(id);

    }

    // Busca los bancos en la base de datos
    @Override
    public List<Banco> listarBancos() {
        
        return bancos.listarBancos();
    }
    
    // Inserta un banco en la base de datos
    @Override
    public void insertarBanco(Banco banco) {

        bancos.insertar(banco);

    }
    
    

/* * * * * * * * * * * * * *
 * * * * * * * * * * * * * *
 * *                     * *
 * *   MÉTODOS PRIVADOS  * *
 * *                     * *
 * * * * * * * * * * * * * *
 * * * * * * * * * * * * * */

    // Este método inserta datos de prueba en las tablas
    public void insertarDatos() {

        insertarBancos();

        insertarUsuarios();

        insertarCuentas();

        agregarCuentasBizumUsuarios();

    }

    // Este método inserta datos de prueba en la tabla "bancos"
    private void insertarBancos() {

        for (int i = 1; i <= 5; i++) {

            Banco banco = new Banco();

            banco.setNombre("Banco Prueba " + i);

            insertarBanco(banco);

        }

    }

    // Este método inserta datos de prueba en la tabla "usuarios"
    private void insertarUsuarios() {

        for (int i = 1; i <= 5; i++) {

            Usuario usuario = new Usuario();

            usuario.setNombre("Nombre Prueba " + i);

            usuario.setApellidos("Apellidos Prueba " + i);

            usuario.setTelefono("00000000" + i);

            usuario.setEmail("email" + i + "@prueba.com");

            usuario.setPassword("passwordPrueba" + i);

            usuario.setCuentaBizum(null);

            insertarUsuario(usuario);

        }

    }

    // Este método inserta datos de prueba en la tabla "cuentas"
    private void insertarCuentas() {
    
        Banco banco1 = buscarBancoPorId(1);
        Banco banco2 = buscarBancoPorId(2);
        Banco banco3 = buscarBancoPorId(3);
    
        for (int i = 1; i <= 5; i++) {
        
            Usuario usuario = buscarUsuarioPorId(i);
        
            for (int j = 1; j <= 3; j++) {
            
                Cuenta cuenta = new Cuenta();
            
                cuenta.setDinero(1000.00);

                cuenta.setUsuario(usuario);

                if (j == 1) {

                    cuenta.setBanco(banco1);

                } else if (j == 2) {

                    cuenta.setBanco(banco2);

                } else {

                    cuenta.setBanco(banco3);

                }
            
                insertarCuenta(cuenta);
            
            }
        
        }
    
    }

    // Este método actualiza la cuentaBizum de todos los usuarios a su cuenta del banco con ID 1
    private void agregarCuentasBizumUsuarios() {

        Banco banco1 = buscarBancoPorId(1);

        List<Cuenta> cuentasBanco1 = buscarCuentaPorBanco(banco1);

        for (Cuenta cuenta : cuentasBanco1) {

            Usuario usuario = cuenta.getUsuario();

            usuario.setCuentaBizum(cuenta);

            modificarUsuario(usuario);

        }

    }

}