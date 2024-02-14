package es.gestor_bancos.gestorbancos.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.gestor_bancos.gestorbancos.modelo.entidades.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Integer> {

    // Busca un banco a partir de su id; devuelve el nombre del banco
    @Query("SELECT b FROM Banco b WHERE b.id = :id")
    Banco buscarBancoPorId(@Param("id") int id);

    // Busca un banco a partir de su id; devuelve el nombre del banco
    @Query("SELECT b FROM Banco b")
    List<Banco> listarBancos();

    // Añade un nuevo banco
    default void insertar(Banco banco) {

        save(banco);

    }






    // // Busca bancos a partir de su nombre; devuelve la id de los bancos
    // @Query("SELECT b.id FROM Banco b WHERE b.nombre = :nombre")
    // List<Integer> buscarIdPorNombre(@Param("nombre") String nombre);



    // // Actualiza el nombre del banco a partir del id
    // default void modificar(Integer id, String nombre) {
    //     Banco banco = findById(id).orElseThrow(() -> new RuntimeException("Banco no encontrado"));
    //     banco.setNombre(nombre);
    //     save(banco);
    // }
    
    // // Borra un banco a partir del id
    // default void eliminar(Integer id) {
    //     deleteById(id);
    // }

}