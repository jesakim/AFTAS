package com.competition.aftas.config;


import com.competition.aftas.enums.Role;
import com.competition.aftas.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private static final String[] WHITE_LIST = {
            "/api/auth/**"
    };

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authenticationProvider;

    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req
                                .requestMatchers(WHITE_LIST).permitAll()
                                .requestMatchers(HttpMethod.GET,"competitions/**").hasAnyAuthority(Role.MEMBER.name(), Role.JUDGE.name(), Role.MANAGER.name())
                                .requestMatchers(HttpMethod.POST,"competitions").hasAnyAuthority(Role.JUDGE.name(), Role.MANAGER.name())
                                .requestMatchers(HttpMethod.DELETE,"competitions/**").hasAnyAuthority(Role.JUDGE.name(), Role.MANAGER.name())
                                .requestMatchers("/**").hasAnyAuthority(Role.MANAGER.name())
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((req, res, auth) -> {
                                    SecurityContextHolder.clearContext();
                                })
                )
                .build();
    }

}
