package cz.uhk.sk_web.config;

import cz.uhk.sk_web.exception.CustomAuthenticationException;
import cz.uhk.sk_web.service.CustomUserDetailService;
import cz.uhk.sk_web.util.LogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@ComponentScan("cz.uhk.sk_web")
public class WebSecurityConfig {
    private final CustomUserDetailService userDetailsService;

    public WebSecurityConfig(CustomUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(
                                mvcMatcherBuilder.pattern("/login**"),
                                mvcMatcherBuilder.pattern("/signup**"),
                                mvcMatcherBuilder.pattern("/save-user**"),
                                mvcMatcherBuilder.pattern("/"),
                                mvcMatcherBuilder.pattern("/img/**"),
                                mvcMatcherBuilder.pattern("/css/**"),
                                mvcMatcherBuilder.pattern("/index**"),
                                mvcMatcherBuilder.pattern("/gallery**"),
                                mvcMatcherBuilder.pattern("/posts**"),
                                mvcMatcherBuilder.pattern("/about**")
                        ).permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll())
                .exceptionHandling((e) -> e.authenticationEntryPoint((request, response, authException) -> {
                    if (authException != null) {
                        if (authException instanceof CustomAuthenticationException) {
                            LogoutHandler.logout(request, response);
                        }
                        response.sendRedirect("/login?unauthorized");
                    }
                }))
                .logout(LogoutConfigurer::permitAll).build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}