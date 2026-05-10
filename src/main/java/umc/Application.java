package umc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication	// BaseEntity의 createdAt, updateAt 자동 저장
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
