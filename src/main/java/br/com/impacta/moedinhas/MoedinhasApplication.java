package br.com.impacta.moedinhas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableConfigurationProperties
@SpringBootApplication
public class MoedinhasApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoedinhasApplication.class, args);
    }

}
