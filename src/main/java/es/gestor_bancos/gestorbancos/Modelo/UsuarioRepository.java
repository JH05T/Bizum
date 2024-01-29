package es.gestor_bancos.gestorbancos.Modelo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    @Query("SELECT ID, NOMBRE, APELLIDOS, TELEFONO, PASSWORD, CUENTA_BIZUM FROM USUARIOS WHERE ID = :id")
    List<Usuario> buscarPorId(@Param("id") int id);

    @Modifying
    @Query("INSERT INTO USUARIOS (NOMBRE, APELLIDOS, TELEFONO, PASSWORD, CUENTA_BIZUM) VALUES (:nombre, :apellidos, :telefono, :password, :cuentaBizum)")
    void insertar(@Param("nombre") String nombre, @Param("apellidos") String apellidos, @Param("telefono") String telefono, @Param("password") String password, @Param("cuentaBizum") int cuentaBizum);

    @Modifying
    @Query("UPDATE USUARIOS SET NOMBRE = :nombre, APELLIDOS = :apellidos, TELEFONO = :telefono, PASSWORD = :password, CUENTA_BIZUM = :cuentaBizum WHERE ID = :id")
    void modificar(@Param("id") Integer id, @Param("nombre") String nombre, @Param("apellidos") String apellidos, @Param("telefono") String telefono, @Param("password") String password, @Param("cuentaBizum") int cuentaBizum);

    @Modifying
    @Query("DELETE FROM USUARIOS WHERE ID = :id")
    void eliminar(@Param("id") Integer id);

}