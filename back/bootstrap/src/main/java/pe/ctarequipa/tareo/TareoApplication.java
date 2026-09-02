package pe.ctarequipa.tareo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pe.ctarequipa.tareo")
public class TareoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TareoApplication.class, args);
    }
}
