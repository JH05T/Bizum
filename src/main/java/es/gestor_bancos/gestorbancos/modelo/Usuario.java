package es.gestor_bancos.gestorbancos.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    private int id;

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @Column
    private String telefono;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private int cuentaBizum;

    public Usuario(int id, String nombre, String apellidos, String telefono, String email, String password, int cuentaBizum) {

        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.cuentaBizum = cuentaBizum;

    }

    public Usuario(String nombre, String apellidos, String telefono, String email, String password, int cuentaBizum) {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
