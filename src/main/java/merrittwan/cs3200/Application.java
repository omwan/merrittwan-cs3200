package merrittwan.cs3200;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application class.
 */
@SpringBootApplication
@ComponentScan("merrittwan.cs3200")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
