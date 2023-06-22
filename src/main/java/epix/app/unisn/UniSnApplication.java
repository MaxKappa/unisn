package epix.app.unisn;

import epix.app.unisn.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class UniSnApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniSnApplication.class, args);
	}



}
