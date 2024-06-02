package com.itexclusive.toolsrental_mvc.model.security;


import com.itexclusive.toolsrental_mvc.model.dao.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserRepository userRepository;
    private final DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // Отключаем CSRF для простоты
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/error").permitAll()
                // Определение страниц доступных анонимам
                .requestMatchers("/register", "/login").anonymous()
                // Определение страниц доступных вошедшим
                .requestMatchers("/logout", "/checkout", "/categories").authenticated()
                // Везде можно ходить админу и тестировщику
                .requestMatchers("/**").hasAnyRole("ADMIN", "TEST")
                // Все остальные запросы доступны только с антентификацией
                .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
                // Задание страницы для входа
                .loginPage("/login")
                .defaultSuccessUrl("/profile")
//                .failureUrl("/login?error=true") // см. контроллер - тоже закомментированная строка
                    .failureUrl("/login")
            )
            .logout(logout -> logout
                // Страница для выхода
//                .logoutUrl("/logout") // строка идентична следующей строке
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .logoutSuccessUrl("/login")
            );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
            .ignoring()
            .requestMatchers("https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/**")
            .requestMatchers("/styles/*.css", "/*.css", "/img/**");
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImplementation(userRepository);
    }

    @Bean
    protected BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public UserDetailsManager userDetailsManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService())
            .passwordEncoder(encoder())
            .and()
            .authenticationProvider(daoAuthenticationProvider())
            .build();

        JdbcUserDetailsManager jdbcManager = new JdbcUserDetailsManager(dataSource);
        jdbcManager.setAuthenticationManager(authenticationManager);
        return jdbcManager;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(encoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());;
        return daoAuthenticationProvider;
    }
}

