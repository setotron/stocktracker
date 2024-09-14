package com.ifp.invt_tfg.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {

    @Bean
    public UserDetailsManager usersCustom(DataSource dataSource){
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("select Username, Password, Status from Usuarios where Username=?");
            users.setAuthoritiesByUsernameQuery("select u.username, r.roles from RolesUsuarios ur "+
                    "inner join usuarios u on u.id = ur.idUsuarios "+
                    "inner join roles r on r.id = ur.idRoles where u.username = ?");

        return users;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()

                

                .requestMatchers("/bootstrap/**", "/images/**", "/tinymce/**","/signup","/bcrypt/**","/about").permitAll()

                .requestMatchers("/").authenticated()

                // Los recursos estáticos no requieren autenticación


                // Las vistas públicas no requieren autenticación
                .requestMatchers("/login","/bcrypt/**").permitAll()

                //.requestMatchers("/solicitudes/create/**","/solicitudes/save/**").hasAnyAuthority("USUARIO")

                .requestMatchers("/productos/++").hasAnyAuthority("ADMINISTRADOR")
                //.requestMatchers("/vacantes/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
                //.requestMatchers("/categorias/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
                //.requestMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR")


                // Todas las demás URLs de la Aplicación requieren autenticació

                .anyRequest().authenticated()

                // El formulario de Login no requiere autenticacion
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll();

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncode() {
        return new BCryptPasswordEncoder();
    }






}
