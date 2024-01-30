package es.gestor_bancos.gestorbancos.Modelo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    // Busca un usuario a partir del id; devuelve todos los datos
    @Query("SELECT NOMBRE, APELLIDOS, TELEFONO, PASSWORD, CUENTA_BIZUM FROM USUARIOS WHERE ID = :id")
    Optional<Usuario> buscarDatosPorId(@Param("id") int id);

    // Busca un usuario a partir del id; devuelve la contraseña
    @Query("SELECT PASSWORD FROM USUARIOS WHERE ID = :id")
    String buscarPasswordPorId(@Param("id") int id);

    // Busca un usuario a partir del id; devuelve la cuenta Bizum
    @Query("SELECT CUENTA_BIZUM FROM USUARIOS WHERE ID = :id")
    int buscarCuentaBizumPorId(@Param("id") int id);

    // Busca un usuario a partir del id; devuelve el nombre y los apellidos del usuario
    @Query("SELECT NOMBRE, APELLIDOS FROM USUARIOS WHERE ID = :id")
    List<String> buscarNombreYApellidosPorId(@Param("id") int id);

    // Busca un usuario a partir del número de teléfono; devuelve la cuenta Bizum
    @Query("SELECT CUENTA_BIZUM FROM USUARIOS WHERE TELEFONO = :telefono")
    int buscarCuentaBizumPorTelefono(@Param("telefono") String telefono);

    // Añade un nuevo usuario
    @Modifying
    @Query("INSERT INTO USUARIOS (NOMBRE, APELLIDOS, TELEFONO, PASSWORD, CUENTA_BIZUM) VALUES (:nombre, :apellidos, :telefono, :password, :cuentaBizum)")
    void insertar(@Param("nombre") String nombre, @Param("apellidos") String apellidos, @Param("telefono") String telefono, @Param("password") String password, @Param("cuentaBizum") int cuentaBizum);

    // Actualiza los datos de un usuario a partir del id
    @Modifying
    @Query("UPDATE USUARIOS SET NOMBRE = :nombre, APELLIDOS = :apellidos, TELEFONO = :telefono, PASSWORD = :password, CUENTA_BIZUM = :cuentaBizum WHERE ID = :id")
    void modificar(@Param("id") Integer id, @Param("nombre") String nombre, @Param("apellidos") String apellidos, @Param("telefono") String telefono, @Param("password") String password, @Param("cuentaBizum") int cuentaBizum);

    // Borra un usuario a partir del id
    @Modifying
    @Query("DELETE FROM USUARIOS WHERE ID = :id")
    void eliminar(@Param("id") Integer id);

}