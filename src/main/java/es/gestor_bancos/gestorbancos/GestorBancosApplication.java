package es.gestor_bancos.gestorbancos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import es.gestor_bancos.gestorbancos.Modelo.GestorDB;

@SpringBootApplication
public class GestorBancosApplication {

	public static void main(String[] args) {

		//SpringApplication.run(GestorBancosApplication.class, args);
		ConfigurableApplicationContext contexto = SpringApplication.run(GestorDB.class, args);
		GestorDB gestorDB = contexto.getBean(GestorDB.class);
		gestorDB.conectar();
		gestorDB.desconectar();
		contexto.close();

	}

}
