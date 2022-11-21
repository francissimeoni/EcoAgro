package com.EcoAgro.EcoAgro;

import com.EcoAgro.EcoAgro.Servicios.UsuariosServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWeb extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UsuariosServicios usuarioServicios;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder administreadorDeAutenticaciones) throws Exception {
        
        administreadorDeAutenticaciones
                .userDetailsService(usuarioServicios)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       
        http.authorizeHttpRequests()
                .antMatchers("/admin/*").hasRole("ADMINISTRADOR")
                 .antMatchers("/admin/*").hasRole("PRODUCTOR")
                .antMatchers("/css/*", "/js/*", "/img/*", "/**")
                .permitAll()
               
                .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loguearse")
                .usernameParameter("user")
                .passwordParameter("pasword")
                .defaultSuccessUrl("/PaginaPrincipal")
                .permitAll()
                
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/PaginaPrincipal")
                .permitAll()
                .and().csrf()
                .disable();
    }
    
}
