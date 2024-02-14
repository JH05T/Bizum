package es.gestor_bancos.gestorbancos.modelo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gestor_bancos.gestorbancos.modelo.repositorios.BancoRepository;
import es.gestor_bancos.gestorbancos.modelo.repositorios.CuentaRepository;
import es.gestor_bancos.gestorbancos.modelo.repositorios.UsuarioRepository;

import es.gestor_bancos.gestorbancos.modelo.entidades.Banco;
import es.gestor_bancos.gestorbancos.modelo.entidades.Cuenta;
import es.gestor_bancos.gestorbancos.modelo.entidades.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Service
public class GestorDB implements IGestorDB {

/* * * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * * * *
 * *                             * *
 * *   CONSTANTES Y VARIABLES    * *
 * *                             * *
 * * * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * * * */

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bancos";
    private static final String USER = "J";
    private static final String PASSWORD = "";
    private static Connection conn;

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


    // Este método inicia la conexión con la base de datos
    public void conectar() {

        try {

            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

        } catch (SQLException e) {

            crearDB();

        }

    }

    // Este método finaliza la conexión con la base de datos
    public void desconectar(){

        try {

            conn.close();

        } catch (Exception e) {

        }

    }

    // Este método inserta datos de prueba en las tablas
    public void insertarDatos() {

        insertarBancos();

        insertarUsuarios();

        insertarCuentas();

        agregarCuentasBizumUsuarios();

    }



/* * * * * * * * * * * * * *
 *                         *
 *   GESTIÓN DE USUARIOS   *
 *                         *
 * * * * * * * * * * * * * */

    @Override
    public void insertarUsuario(Usuario usuario) {

        usuarios.insertar(usuario);

    }


    
/* * * * * * * * * * * * *
 *                       *
 *   GESTIÓN DE CUENTAS  *
 *                       *
 * * * * * * * * * * * * */

    @Override
    public Cuenta buscarCuentaPorId(int id) {

        return cuentas.buscarCuentaPorId(id);

    }
  
    @Override
    public List<Cuenta> buscarCuentaPorBanco(Banco banco) {

        return cuentas.buscarCuentaPorBanco(banco);

    }

    @Override
    public List<Cuenta> listarCuentasDeUsuario(Usuario usuario) {
        
        return cuentas.listarCuentasPorUsuario(usuario);

    }

    @Override
    public void insertarCuenta(Cuenta cuenta) {

        cuentas.insertar(cuenta);

    }

    @Override
    public void modificarCuenta(Cuenta cuenta) {

        cuentas.modificar(cuenta);
    }

    @Override
    public void eliminarCuenta(int id) {

        cuentas.eliminar(id);

    }



/* * * * * * * * * * * * *
 *                       *
 *   GESTIÓN DE BANCOS   *
 *                       *
 * * * * * * * * * * * * */

    @Override
    public Optional<Banco> buscarBancoPorId(int id) {

        return bancos.buscarDatosPorId(id);

    }

    @Override
    public List<Banco> listarBancos() {

        return bancos.listarBancos();
    }

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

    // Este método crea la base de datos junto a todas sus tablas si no existen
    private void crearDB() {

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", USER, PASSWORD);
            Statement statement = conn.createStatement();

            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS bancos;";
            statement.executeUpdate(createDatabaseQuery);
    
            statement.execute("USE bancos;");
    
            crearTablas(statement);

            conectar();

            statement.close();
    
        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    // Este método crea las tablas de la base de datos si no existen
    private void crearTablas(Statement statement) throws SQLException {

        crearTablaBancos(statement);
   
        empezarACrearTablaUsuarios(statement);
   
        crearTablaCuentas(statement);
   
        terminarDeCrearTablaUsuarios(statement);

    }

    // Este método crea la tabla "bancos" de la base de datos si no existe
    private void crearTablaBancos(Statement statement) throws SQLException {

        String createBancosTableQuery = "CREATE TABLE IF NOT EXISTS bancos (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(255)" +
                ");";

        statement.executeUpdate(createBancosTableQuery);

    }

    // Este método crea la tabla "usuarios" de la base de datos si no existe
    private void empezarACrearTablaUsuarios(Statement statement) throws SQLException {

        String createUsuariosTableQuery = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(255)," +
                "apellidos VARCHAR(255)," +
                "telefono VARCHAR(15)," +
                "email VARCHAR(255)," +
                "password VARCHAR(255)" +
                ");";

        statement.executeUpdate(createUsuariosTableQuery);

    }

    // Este método crea la tabla "cuentas" de la base de datos si no existe
    private void crearTablaCuentas(Statement statement) throws SQLException {

        String createCuentasTableQuery = "CREATE TABLE IF NOT EXISTS cuentas (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "dinero DECIMAL(10, 2)," +
                "usuario INT," +
                "banco INT," +
                "FOREIGN KEY (usuario) REFERENCES usuarios(id)," +
                "FOREIGN KEY (banco) REFERENCES bancos(id)" +
                ");";

        statement.executeUpdate(createCuentasTableQuery);

    }

    // Este método añade a la tabla usuarios una nueva columna "cuenta_bizum" que es una clave foránea que indica la cuenta del usuario que tiene bizum
    private void terminarDeCrearTablaUsuarios(Statement statement) throws SQLException {

        String alterUsuariosTableQuery = "ALTER TABLE usuarios " +
                "ADD COLUMN cuenta_bizum INT, " +
                "ADD FOREIGN KEY (cuenta_bizum) REFERENCES cuentas(id);";

        statement.executeUpdate(alterUsuariosTableQuery);

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
    
        Banco banco1 = buscarBancoPorId(1).orElse(null);
        Banco banco2 = buscarBancoPorId(2).orElse(null);
        Banco banco3 = buscarBancoPorId(3).orElse(null);
    
        for (int i = 1; i <= 5; i++) {
        
            Usuario usuario = usuarios.buscarDatosPorId(i).orElse(null);
        
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

        Banco banco1 = bancos.findById(1).orElse(null);

        List<Cuenta> cuentasBanco1 = buscarCuentaPorBanco(banco1);

        for (Cuenta cuenta : cuentasBanco1) {

            Usuario usuario = cuenta.getUsuario();

            usuario.setCuentaBizum(cuenta);

            usuarios.save(usuario);

        }

        usuarios.flush();

    }

}
