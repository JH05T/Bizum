package es.gestor_bancos.gestorbancos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import es.gestor_bancos.gestorbancos.Modelo.GestorDB;

@SpringBootApplication
@EnableJdbcRepositories(basePackages = "es.gestor_bancos.gestorbancos.modelo")
@ComponentScan(basePackages = "es.gestor_bancos.gestorbancos.modelo")
@EntityScan(basePackages = "es.gestor_bancos.gestorbancos.modelo")
public class GestorBancosApplication {

	public static void main(String[] args) {

		//SpringApplication.run(GestorBancosApplication.class, args);
		ConfigurableApplicationContext contexto = SpringApplication.run(GestorBancosApplication.class, args);
		GestorDB gestorDB = contexto.getBean(GestorDB.class);
		gestorDB.conectar();
		gestorDB.desconectar();
		contexto.close();

	}

}
