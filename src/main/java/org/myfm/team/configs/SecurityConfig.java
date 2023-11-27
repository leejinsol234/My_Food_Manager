package org.myfm.team.configs;

import jakarta.servlet.http.HttpServletResponse;
import org.myfm.team.models.member.LoginFailureHandler;
import org.myfm.team.models.member.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableConfigurationProperties(FileUploadConfig.class)
public class SecurityConfig {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /* 인증 설정 - 로그인 S */
        http.formLogin(f -> {
            f.loginPage("/member/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(new LoginSuccessHandler())
                    .failureHandler(new LoginFailureHandler());
        }); // DSL

        http.logout(c -> {
            c.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .logoutSuccessUrl("/member/login");
        });
        /* 인증 설정 - 로그인 E */

        http.headers(c -> {
            c.frameOptions(o -> o.sameOrigin());
        });






        http.exceptionHandling(c -> {
            c.authenticationEntryPoint((req, resp, e) -> {
                String URI = req.getRequestURI();
                if (URI.indexOf("/admin") != -1) { // 관리자 페이지 - 401 응답 코드
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "NOT AUTHORIZED");
                }
            });
        });

        /* 인가 설정 - 접근 통제 E */

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
