package es.gestor_bancos.gestorbancos.Modelo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BancoRepository extends CrudRepository<Banco, Integer> {

    // Busca un banco a partir de su id; devuelve el nombre del banco
    @Query("SELECT NOMBRE FROM BANCOS WHERE ID = :id")
    String buscarNombrePorId(@Param("id") int id);

    // Busca bancos a partir de su nombre; devuelve la id de los bancos
    @Query("SELECT ID FROM BANCOS WHERE NOMBRE = :nombre")
    List<Integer> buscarIdPorNombre(@Param("nombre") String nombre);

    // Añade un nuevo banco
    @Modifying
    @Query("INSERT INTO BANCOS (NOMBRE) VALUES (:nombre)")
    void insertar(@Param("nombre") String nombre);

    // Actualiza el nombre del banco a partir del id
    @Modifying
    @Query("UPDATE BANCOS SET NOMBRE = :nombre WHERE ID = :id")
    void modificar(@Param("id") Integer id, @Param("nombre") String nombre);

    // Borra un banco a partir del id
    @Modifying
    @Query("DELETE FROM BANCOS WHERE ID = :id")
    void eliminar(@Param("id") Integer id);

}