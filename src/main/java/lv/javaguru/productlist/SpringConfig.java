package lv.javaguru.productlist;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "lv.javaguru.productlist")
@PropertySource("classpath:application.properties")
public class SpringConfig {

}
