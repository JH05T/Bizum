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

}