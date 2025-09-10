package com.matheusiowa12.FipeProject;

import com.matheusiowa12.FipeProject.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FipeProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipeProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.showMenu();
	}
}
