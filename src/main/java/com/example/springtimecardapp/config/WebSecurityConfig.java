package com.example.springtimecardapp.config;

import com.example.springtimecardapp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/images/**",
                "/css/**",
                "/javascript/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // AUTHORIZE
                .authorizeRequests()
                    .mvcMatchers("/signup").permitAll()
                    .mvcMatchers("/register").permitAll()
                    .anyRequest()
                        .authenticated()
                // LOGIN
                .and().formLogin()
                    .loginPage("/login")
                        .permitAll()
                    .defaultSuccessUrl("/success")
                // LOGOUT
                .and().logout()
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true).permitAll()
                ;

        // メッセージをカスタマイズするために、メッセージソースを設定する
        AuthenticationManager manager = this.authenticationManager();
        if (manager instanceof ProviderManager) {
            ProviderManager providerManager = (ProviderManager)manager;
            providerManager.getProviders().forEach(p -> {
                if (p instanceof MessageSourceAware) {
                    ((MessageSourceAware)p).setMessageSource(messageSource);
                }
            });
        }
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }


}
