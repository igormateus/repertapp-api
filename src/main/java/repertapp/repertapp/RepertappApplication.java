package repertapp.repertapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RepertappApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepertappApplication.class, args);
	}

	// @Bean
	// CommandLineRunner run(RepertappUserRepository userRepository){
	// 	return args -> IntStream.rangeClosed(1, 30).forEach(i -> {
	// 		RepertappUser user = new RepertappUser();
	// 		user.setUsername("user" + i);
	// 		user.setEmail("user" + i + "@mail.com");
	// 		user.setPassword("password");
	// 		userRepository.save(user);
	// 	});
	// }
}
