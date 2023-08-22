package co.tanjona.man.jillo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"co.tanjona.man.jillo", "controller", "service", "repository"})
public class JilloApplication {

    public static void main(String[] args) {
        SpringApplication.run(JilloApplication.class, args);
    }

}
