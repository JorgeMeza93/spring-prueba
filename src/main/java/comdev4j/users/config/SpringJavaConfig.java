package comdev4j.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SpringJavaConfig {
	
/*	@Bean
	public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin").password(bCryptPasswordEncoder.encode("userPass")).roles("ADMIN").build());
		manager.createUser(User.withUsername("user").password(bCryptPasswordEncoder.encode("userPass")).roles("USER").build());
		return manager;
	} */
	/*@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}*/
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf()
	      .disable()
	      .authorizeRequests()
	      .antMatchers("/users/**")
	      .hasAnyRole("ADMIN")
	      .antMatchers("/roles/**")
	      .permitAll()
	      .anyRequest()
	      .authenticated()
	      .and()
	      .httpBasic();
	    return http.build();
	}
}
