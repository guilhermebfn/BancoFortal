package com.guilherme.bancofortal;

import com.guilherme.bancofortal.entidades.Cliente;
import com.guilherme.bancofortal.repositorios.RepoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BancoFortalApplication {

	@Bean
	public CommandLineRunner teste(@Autowired RepoCliente repoCliente) {
		return args -> {

			String oi = "oi";

			System.out.println(oi);

			repoCliente.save(new Cliente("guilherme"));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BancoFortalApplication.class, args);
	}

}
