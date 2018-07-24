package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@PropertySource("classpath:application.properties")
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RestController
    @RequestMapping("/api")
    public class ApiController {
        @Value(value="${greeting.msg}")
        private String greeting;

        @GetMapping("/greeting")
        public String greeting(){
            return "Hello world from API";
        }

    }
}
