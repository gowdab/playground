package com.bg.playground.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "clamavservice")
@Configuration
@Data
public class ClamAVProperties {

    private String uploadDir;
    private String clamAvHost;
    private Integer clamAvPort;
}