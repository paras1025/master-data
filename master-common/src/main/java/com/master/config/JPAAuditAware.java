package com.master.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditProvider")
@Profile("!test")
public class JPAAuditAware {

    @Bean
    public AuditorAware<String> auditProvider() {
        return new AuditAware();
    }
}
