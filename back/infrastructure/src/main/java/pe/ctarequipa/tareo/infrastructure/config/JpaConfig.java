package pe.ctarequipa.tareo.infrastructure.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EntityScan(basePackages = "pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity")
@EnableJpaRepositories(basePackages = "pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository")
public class JpaConfig {}
