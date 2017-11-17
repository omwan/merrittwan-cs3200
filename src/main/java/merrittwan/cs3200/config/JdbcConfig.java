package merrittwan.cs3200.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by olivi on 11/09/2017.
 */
@Configuration
@ComponentScan("merrittwan.cs3200")
public class JdbcConfig {

  @Value("${data.source.url}")
  private String dataSourceUrl;

  @Value("${data.source.user}")
  private String dataSourceUser;

  @Value("${data.source.password}")
  private String dataSourcePassword;

  @Bean
  public DataSource mysqlDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl(dataSourceUrl);
    dataSource.setUsername(dataSourceUser);
    dataSource.setPassword(dataSourcePassword);

    return dataSource;
  }
}