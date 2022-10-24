//package africa.semicolon.lumexpress.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return  http.cors().and().csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/api/v1/customer/register").permitAll()
//                .and()
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/api/v1/customer/all").authenticated()
//                .and()
//                .build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
