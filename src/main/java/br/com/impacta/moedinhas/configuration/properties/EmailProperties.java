package br.com.impacta.moedinhas.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "app.email")
public class EmailProperties {

    private String from;

    private String subject;

    private String content;

    private String redirectUrl;

}
