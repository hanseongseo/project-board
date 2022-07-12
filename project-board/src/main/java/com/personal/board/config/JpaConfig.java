package com.personal.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    public AuditorAware<String> auditorAware() {   //  TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때, 수정
        return () -> Optional.of("seeman94");
    }
}
