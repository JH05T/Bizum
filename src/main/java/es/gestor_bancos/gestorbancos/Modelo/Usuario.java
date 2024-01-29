package es.gestor_bancos.gestorbancos.Modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("USUARIOS")
public class Usuario {

    @Id
    private int id;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String password;
    private int cuentaBizum;

    public Usuario(int id, String nombre, String apellidos, String telefono, String password, int cuentaBizum) {

        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.password = password;
        this.cuentaBizum = cuentaBizum;

    }


    /*
     * Getters & Setters
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCuentaBizum() {
        return cuentaBizum;
    }

    public void setCuentaBizum(int cuentaBizum) {
        this.cuentaBizum = cuentaBizum;
    }
    
}
