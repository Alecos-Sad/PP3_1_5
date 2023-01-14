package by.sadovnick.springbootfinal.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final SuccessUserHandler successUserHandler;

  @Autowired
  public WebSecurityConfig(SuccessUserHandler successUserHandler) {
    this.successUserHandler = successUserHandler;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
//              .csrf().disable();
        .csrf().ignoringAntMatchers("/admin/gateway/**")
        .and()
        .authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .successHandler(successUserHandler)
        .permitAll()
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login")
        .permitAll();
  }

  @Bean
  public PasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}