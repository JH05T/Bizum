package es.gestor_bancos.gestorbancos.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.gestor_bancos.gestorbancos.modelo.entidades.Banco;
import es.gestor_bancos.gestorbancos.modelo.entidades.Cuenta;
import es.gestor_bancos.gestorbancos.modelo.entidades.Usuario;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {


    // Busca una cuenta a partir de su id; devuelve la cuenta
    @Query("SELECT c FROM Cuenta c WHERE c.id = :id")
    Cuenta buscarCuentaPorId(@Param("id") int id);

    // Busca las cuentas de un banco; devuevlve la cuenta
    @Query("SELECT c FROM Cuenta c WHERE c.banco = :banco")
    List<Cuenta> buscarCuentaPorBanco(@Param("banco") Banco banco);
        
    // Busca las cuentas de un usuario; devuevlve las cuentas del usuario
    @Query("SELECT c FROM Cuenta c WHERE c.usuario = :usuario")
    List<Cuenta> listarCuentasPorUsuario(@Param("usuario") Usuario usuario);

    // Añade una nueva cuenta
    default void insertar(Cuenta cuenta) {

        save(cuenta);

    }

    // Modifica una cuenta existente
    default void modificar(Cuenta cuenta) {

        if (existsById(cuenta.getId())) {

            save(cuenta);

        } else {

            throw new IllegalArgumentException("No se puede modificar una cuenta que no existe");

        }

    }
  
    // Borra una cuenta a partir del id
    default void eliminar(Integer id) {

        deleteById(id);
        
    }




    // // Busca una cuenta a partir de su id; devuelve todos los datos
    // @Query("SELECT c FROM Cuenta c")
    // List<Cuenta> listarCuentas();

    // // Busca las cuentas de un usuario; devuevlve el dinero y el banco de cada cuenta
    // @Query("SELECT c.banco FROM Cuenta c WHERE c.usuario = :usuario")
    // List<Integer> buscarBancoPorUsuario(@Param("usuario") int usuario);
    
    // // Busca la cuenta de un usuario de un banco concreto; devuevlve la id de la cuenta
    // @Query("SELECT c.id FROM Cuenta c WHERE c.usuario = :usuario AND c.banco = :banco")
    // int buscarIdPorUsuarioYBanco(@Param("usuario") int usuario, @Param("banco") int banco);

    // // Busca la cuenta de un usuario de un banco concreto; devuevlve el dinero de la cuenta
    // @Query("SELECT c.dinero FROM Cuenta c WHERE c.usuario = :usuario AND c.banco = :banco")
    // double buscarDineroPorUsuarioYBanco(@Param("usuario") int usuario, @Param("banco") int banco);

    
    // // Actualiza los datos de una cuenta a partir del id
    // // default void modificar(Integer id, double dinero, int usuario, int banco) {
    // //     Cuenta cuenta = findById(id).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    // //     cuenta.setDinero(dinero);
    // //     cuenta.setUsuario(usuario);
    // //     cuenta.setBanco(banco);
    // //     save(cuenta);
    // // }
    
    // // Actualiza el dinero del banco a partir de su id
    // default void modificarDinero(Integer id, double dinero) {
    //     Cuenta cuenta = findById(id).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    //     cuenta.setDinero(dinero);
    //     save(cuenta);
    // }

}