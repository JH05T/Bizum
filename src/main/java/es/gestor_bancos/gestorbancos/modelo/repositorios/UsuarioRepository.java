package es.gestor_bancos.gestorbancos.modelo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.gestor_bancos.gestorbancos.modelo.entidades.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Busca un usuario a partir del id; devuelve el usuario
    @Query("SELECT u FROM Usuario u WHERE u.id = :id")
    Usuario buscarUsuarioPorId(@Param("id") int id);

    // Busca un usuario a partir del telefono; devuelve el usuario
    @Query("SELECT u FROM Usuario u WHERE u.telefono = :telefono")
    Usuario buscarUsuarioPorTelefono(@Param("telefono") String telefono);

    // Busca un usuario a partir del email; devuelve el usuario
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario buscarUsuarioPorEmail(@Param("email") String email);

    // Añade un nuevo usuario
    default void insertar(Usuario usuario) {

        save(usuario);

    }

    // Modifica un usuario existente
    default void modificar(Usuario usuario) {

        if (existsById(usuario.getId())) {

            save(usuario);

        } else {

            throw new IllegalArgumentException("No se puede modificar un usuario que no existe");

        }

    }

}