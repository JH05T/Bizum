package es.gestor_bancos.gestorbancos.Modelo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BancoRepository extends CrudRepository<Banco, Integer> {

    @Query("SELECT ID, NOMBRE FROM BANCOS WHERE ID = :id")
    List<Banco> buscarPorId(@Param("id") int id);

    @Modifying
    @Query("INSERT INTO BANCOS (NOMBRE) VALUES (:nombre)")
    void insertar(@Param("nombre") String nombre);

    @Modifying
    @Query("UPDATE BANCOS SET NOMBRE = :nombre WHERE ID = :id")
    void modificar(@Param("id") Integer id, @Param("nombre") String nombre);

    @Modifying
    @Query("DELETE FROM BANCOS WHERE ID = :id")
    void eliminar(@Param("id") Integer id);

}