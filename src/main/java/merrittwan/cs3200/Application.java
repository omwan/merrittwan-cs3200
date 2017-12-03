package merrittwan.cs3200;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Main application class.
 */
@SpringBootApplication
@ComponentScan("merrittwan.cs3200")
public class Application {

  @Bean
  public WebMvcConfigurerAdapter corsConfigurer() {
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**");
      }
    };
  }
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
