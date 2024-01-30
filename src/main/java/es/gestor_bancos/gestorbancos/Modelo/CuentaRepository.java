package es.gestor_bancos.gestorbancos.Modelo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface CuentaRepository extends CrudRepository<Cuenta, Integer> {

    // Busca una cuenta a partir de su id; devuelve todos los datos
    @Query("SELECT DINERO, USUARIO, BANCO FROM CUENTAS WHERE ID = :id")
    Optional<Cuenta> buscarDatosPorId(@Param("id") int id);

    // Busca las cuentas de un usuario; devuevlve el dinero y el banco de cada cuenta
    @Query("SELECT BANCO FROM CUENTAS WHERE USUARIO = :usuario")
    List<Integer> buscarBancoPorUsuario(@Param("usuario") int usuario);
    
    // Busca la cuenta de un usuario de un banco concreto; devuevlve la id de la cuenta
    @Query("SELECT ID FROM CUENTAS WHERE USUARIO = :usuario AND BANCO = :banco")
    int buscarIdPorUsuarioYBanco(@Param("usuario") int usuario, @Param("banco") int banco);

    // Busca la cuenta de un usuario de un banco concreto; devuevlve el dinero de la cuenta
    @Query("SELECT DINERO FROM CUENTAS WHERE USUARIO = :usuario AND BANCO = :banco")
    double buscarDineroPorUsuarioYBanco(@Param("usuario") int usuario, @Param("banco") int banco);

    // Añade una nueva cuenta
    @Modifying
    @Query("INSERT INTO CUENTAS (DINERO, USUARIO, BANCO) VALUES (:dinero, :usuario, :banco)")
    void insertarCuenta(@Param("dinero") double dinero, @Param("usuario") int usuario, @Param("banco") int banco);

    // Actualiza los datos de una cuenta a partir del id
    @Modifying
    @Query("UPDATE CUENTAS SET DINERO = :dinero, USUARIO = :usuario, BANCO = :banco WHERE ID = :id")
    void modificar(@Param("id") Integer id, @Param("dinero") double dinero, @Param("usuario") int usuario, @Param("banco") int banco);

    // Actualiza el dinero del banco a partir de su id
    @Modifying
    @Query("UPDATE CUENTAS SET DINERO = :dinero WHERE ID = :id")
    void modificarDinero(@Param("id") Integer id, @Param("dinero") double dinero);

    // Borra una cuenta a partir del id
    @Modifying
    @Query("DELETE FROM CUENTAS WHERE ID = :id")
    void eliminar(@Param("id") Integer id);

}