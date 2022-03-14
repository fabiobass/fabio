package br.com.fabio.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import br.com.fabio.curso.domain.Categoria;
import br.com.fabio.curso.resources.CategoriaResource;

@SpringBootApplication
public class FabioApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabioApplication.class, args);
	}

}
