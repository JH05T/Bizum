package es.gestor_bancos.gestorbancos.modelo;

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

@Service
public class GestorDB {

    /* * * * * * * * * * * * * *
     * CONSTANTES Y VARIABLES  *
     * * * * * * * * * * * * * */

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/BANCOS";
    private static final String USER = "J";
    private static final String PASSWORD = "";
    private static Connection conn;

    @Autowired
    private UsuarioRepository usuarios;
    
    @Autowired
    private CuentaRepository cuentas;
    
    @Autowired
    private BancoRepository bancos;
    
    /* * * * * * * * * * *
     * MÉTODOS PÚBLICOS  *
     * * * * * * * * * * */
    

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

    /* * * * * * * * * * *
     * MÉTODOS PRIVADOS  *
     * * * * * * * * * * */

    // Este método crea la base de datos junto a todas sus tablas si no existen
    private void crearDB() {

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", USER, PASSWORD);
            Statement statement = conn.createStatement();

            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS BANCOS;";
            statement.executeUpdate(createDatabaseQuery);
    
            statement.execute("USE BANCOS;");
    
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

    // Este método crea la tabla "BANCOS" de la base de datos si no existe
    private void crearTablaBancos(Statement statement) throws SQLException {

        String createBancosTableQuery = "CREATE TABLE IF NOT EXISTS BANCOS (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY," +
                "NOMBRE VARCHAR(255)" +
                ");";

        statement.executeUpdate(createBancosTableQuery);

    }

    // Este método crea la tabla "USUARIOS" de la base de datos si no existe
    private void empezarACrearTablaUsuarios(Statement statement) throws SQLException {

        String createUsuariosTableQuery = "CREATE TABLE IF NOT EXISTS USUARIOS (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY," +
                "NOMBRE VARCHAR(255)," +
                "APELLIDOS VARCHAR(255)," +
                "TELEFONO VARCHAR(15)," +
                "EMAIL VARCHAR(255)," +
                "PASSWORD VARCHAR(255)" +
                ");";

        statement.executeUpdate(createUsuariosTableQuery);

    }

    // Este método crea la tabla "CUENTAS" de la base de datos si no existe
    private void crearTablaCuentas(Statement statement) throws SQLException {

        String createCuentasTableQuery = "CREATE TABLE IF NOT EXISTS CUENTAS (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY," +
                "DINERO DECIMAL(10, 2)," +
                "USUARIO INT," +
                "BANCO INT," +
                "FOREIGN KEY (USUARIO) REFERENCES USUARIOS(ID)," +
                "FOREIGN KEY (BANCO) REFERENCES BANCOS(ID)" +
                ");";

        statement.executeUpdate(createCuentasTableQuery);

    }

    // Este método añade a la tabla usuarios una nueva columna "CUENTA_BIZUM" que es una clave foránea que indica la cuenta del usuario que tiene bizum
    private void terminarDeCrearTablaUsuarios(Statement statement) throws SQLException {

        String alterUsuariosTableQuery = "ALTER TABLE USUARIOS " +
                "ADD COLUMN CUENTA_BIZUM INT, " +
                "ADD FOREIGN KEY (CUENTA_BIZUM) REFERENCES CUENTAS(ID);";

        statement.executeUpdate(alterUsuariosTableQuery);

    }

}
