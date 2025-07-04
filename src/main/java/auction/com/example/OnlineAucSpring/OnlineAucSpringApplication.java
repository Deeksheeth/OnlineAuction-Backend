package auction.com.example.OnlineAucSpring;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Base64;

@SpringBootApplication
public class OnlineAucSpringApplication {

	public static void main(String[] args) {

//		byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
//		String base64Key = Base64.getEncoder().encodeToString(keyBytes);
//		System.out.println("Secure HS512 Key:\n" + base64Key);

		SpringApplication.run(OnlineAucSpringApplication.class, args);

	}

}
