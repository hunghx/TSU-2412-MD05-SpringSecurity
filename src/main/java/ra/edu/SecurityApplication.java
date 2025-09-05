package ra.edu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ra.edu.config.jwt.JwtProvider;

@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder, JwtProvider provider) {
        return args -> {
            // mk 1
            System.out.println(passwordEncoder.encode("admin123"));
            System.out.println(passwordEncoder.encode("hunghx123"));
            System.out.println(passwordEncoder.encode("123456"));

            System.out.println(provider.generateToken("hunghx@gmail.com"));
            System.out.println(provider.generateToken("admin@gmail.com"));

        };
    }

}
