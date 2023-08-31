package com.example.realestateprojectwithsecurity.Config;


import com.example.realestateprojectwithsecurity.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;


    @Bean
    public DaoAuthenticationProvider authenticationProvider (){
        DaoAuthenticationProvider authenticationProvider =new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{

                 http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

                .and()


                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/agent/regester").permitAll()
                         .requestMatchers("/api/v1/customer/regester").permitAll()

                       .requestMatchers("/api/v1/property/get").hasAuthority("Agent")
                         .requestMatchers("/api/v1/property/get-my").hasAuthority("Agent")
                         .requestMatchers("/api/v1/property/add").hasAuthority("Agent")
                         .requestMatchers("/api/v1/property/update").hasAuthority("Agent")
                         .requestMatchers("/api/v1/property/delete").hasAuthority("Agent")


                         .requestMatchers("/api/v1/property/getbyloc").hasAuthority("Customer")
                         .requestMatchers("/api/v1/property/getbysize").hasAuthority("Customer")
                         .requestMatchers("/api/v1/property/getbysortpriec").hasAuthority("Customer")


                         .requestMatchers("/api/v1/deal/get-agent").hasAuthority("Agent")
                         .requestMatchers("/api/v1/deal/get-coustomesr").hasAuthority("Customer")
                         .requestMatchers("/api/v1/deal/add").hasAuthority("Agent")
                         .requestMatchers("/api/v1/deal/update").hasAuthority("Agent")
                         .requestMatchers("/api/v1/deal/delete").hasAuthority("Agent")


                         .requestMatchers("/api/v1/ratings/get").hasAuthority("Customer")
                         .requestMatchers("/api/v1/ratings/add").hasAuthority("Customer")
                         .requestMatchers("/api/v1/ratings/deletet").hasAuthority("Customer")
                         .requestMatchers("/api/v1/ratings/rating").hasAuthority("Customer")


                .anyRequest().authenticated()



                .and()


                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)


                .and()
                .httpBasic();
        return http.build();

    }












}
