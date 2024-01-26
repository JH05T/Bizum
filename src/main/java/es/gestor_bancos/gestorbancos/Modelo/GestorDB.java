package es.gestor_bancos.gestorbancos.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorDB {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/BANCOS";
    private static final String USER = "J";
    private static final String PASSWORD = "";
    private static Connection conn;

    public void conectar() {

        // Intentar conectar a la base de datos
        try {

            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

        } catch (SQLException e) {

            crearDB();

        }

    }

    private void crearDB() {

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", USER, PASSWORD);
            Statement statement = conn.createStatement();
    
            // Crear la base de datos 'BANCOS' si no existe
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS BANCOS";
            statement.executeUpdate(createDatabaseQuery);
    
            statement.execute("USE BANCOS");
    
            // Crear la tabla 'BANCOS' si no existe
            String createBancosTableQuery = "CREATE TABLE IF NOT EXISTS BANCOS (" +
                    "ID INT AUTO_INCREMENT PRIMARY KEY," +
                    "NOMBRE VARCHAR(255)" +
                    ")";

            statement.executeUpdate(createBancosTableQuery);
    
            // Crear la tabla 'USUARIOS' si no existe
            String createUsuariosTableQuery = "CREATE TABLE IF NOT EXISTS USUARIOS (" +
                    "ID INT AUTO_INCREMENT PRIMARY KEY," +
                    "NOMBRE VARCHAR(255)," +
                    "APELLIDOS VARCHAR(255)," +
                    "TELEFONO VARCHAR(15)," +
                    "PASSWORD VARCHAR(255)," +
                    ")";

            statement.executeUpdate(createUsuariosTableQuery);
    
            // Crear la tabla 'CUENTAS' si no existe
            String createCuentasTableQuery = "CREATE TABLE IF NOT EXISTS CUENTAS (" +
                    "ID INT AUTO_INCREMENT PRIMARY KEY," +
                    "DINERO DECIMAL(10, 2)," +
                    "USUARIO INT," +
                    "BANCO INT," +
                    "FOREIGN KEY (USUARIO) REFERENCES USUARIOS(ID)," +
                    "FOREIGN KEY (BANCO) REFERENCES BANCOS(ID)" +
                    ")";

            statement.executeUpdate(createCuentasTableQuery);
    
            // Agregar la columna CUENTA_BIZUM como clave foránea a la tabla USUARIOS
            String alterUsuariosTableQuery = "ALTER TABLE USUARIOS " +
                    "ADD COLUMN CUENTA_BIZUM INT, " +
                    "ADD FOREIGN KEY (CUENTA_BIZUM) REFERENCES CUENTAS(ID)";

            statement.executeUpdate(alterUsuariosTableQuery);

            conectar();
    
        } catch (SQLException e) {

        }

    }

    private void desconectar(){

        try {

            conn.close();

        } catch (SQLException e) {

        }

    }

}
