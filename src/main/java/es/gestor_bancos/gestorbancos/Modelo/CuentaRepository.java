package es.gestor_bancos.gestorbancos.Modelo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface CuentaRepository extends CrudRepository<Cuenta, Integer> {

    @Query("SELECT ID, DINERO, USUARIO, BANCO FROM CUENTAS WHERE ID = :id")
    List<Cuenta> buscarPorId(@Param("id") int id);

    @Modifying
    @Query("INSERT INTO CUENTAS (DINERO, USUARIO, BANCO) VALUES (:dinero, :usuario, :banco)")
    void insertarCuenta(@Param("dinero") double dinero, @Param("usuario") int usuario, @Param("banco") int banco);

    @Modifying
    @Query("UPDATE CUENTAS SET DINERO = :dinero, USUARIO = :usuario, BANCO = :banco WHERE ID = :id")
    void modificar(@Param("id") Integer id, @Param("dinero") double dinero, @Param("usuario") int usuario, @Param("banco") int banco);

    @Modifying
    @Query("DELETE FROM CUENTAS WHERE ID = :id")
    void eliminar(@Param("id") Integer id);

}