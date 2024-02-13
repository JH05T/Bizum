package es.gestor_bancos.gestorbancos.modelo.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.gestor_bancos.gestorbancos.modelo.entidades.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Busca un usuario a partir del id; devuelve todos los datos
    @Query("SELECT u FROM Usuario u WHERE u.id = :id")
    Optional<Usuario> buscarDatosPorId(@Param("id") int id);

    // Busca un usuario a partir del email; devuelve todos los datos
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Optional<Usuario> buscarDatosPorEmail(@Param("email") int email);

    // Busca un usuario a partir del id; devuelve la contraseña
    @Query("SELECT u.password FROM Usuario u WHERE u.id = :id")
    String buscarPasswordPorId(@Param("id") int id);

    // Busca un usuario a partir del id; devuelve la cuenta Bizum
    @Query("SELECT u.cuentaBizum FROM Usuario u WHERE u.id = :id")
    int buscarCuentaBizumPorId(@Param("id") int id);

    // Busca un usuario a partir del id; devuelve el nombre y los apellidos del usuario
    @Query("SELECT u.nombre, u.apellidos FROM Usuario u WHERE u.id = :id")
    List<String> buscarNombreYApellidosPorId(@Param("id") int id);

    // Busca un usuario a partir del número de teléfono; devuelve la cuenta Bizum
    @Query("SELECT u.cuentaBizum FROM Usuario u WHERE u.telefono = :telefono")
    int buscarCuentaBizumPorTelefono(@Param("telefono") String telefono);

    // Añade un nuevo usuario
    default void insertar(Usuario usuario) {

        save(usuario);

    }

    // Actualiza los datos de un usuario a partir del id
    // default void modificar(Integer id, String nombre, String apellidos, String telefono, String email, String password, int cuentaBizum) {
    //     Usuario usuario = findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    //     usuario.setNombre(nombre);
    //     usuario.setApellidos(apellidos);
    //     usuario.setTelefono(telefono);
    //     usuario.setEmail(email);
    //     usuario.setPassword(password);
    //     usuario.setCuentaBizum(cuentaBizum);
    //     save(usuario);
    // }

    // Borra un usuario a partir del id
    default void eliminar(Integer id) {
        deleteById(id);
    }

}
