package omarp.pruebatecnica;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import omarp.pruebatecnica.entity.User;
import omarp.pruebatecnica.repository.UserRepository;

@SpringBootApplication
public class PruebatecnicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebatecnicaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(UserRepository repository, PasswordEncoder passwordEncoder) {
		return args -> {
			User user = new User();
			user.setUsername("admin@mail.com");
			user.setPassword(passwordEncoder.encode("admin"));
			if ( !repository.findUserByUsername(user.getUsername()).isPresent()   ) repository.save(user);
		};
	}
}
