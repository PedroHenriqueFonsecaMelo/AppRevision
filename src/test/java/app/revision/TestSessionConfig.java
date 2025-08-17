package app.revision;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import app.revision.dto.carrinho.CartServicos;

@TestConfiguration
public class TestSessionConfig {
    @Bean
    public CartServicos cartServicos() {
        return new CartServicos(); // ou um mock, se preferir
    }
}