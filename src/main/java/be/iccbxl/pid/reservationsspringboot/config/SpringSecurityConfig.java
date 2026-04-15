package be.iccbxl.pid.reservationsspringboot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import be.iccbxl.pid.reservationsspringboot.model.User;
import be.iccbxl.pid.reservationsspringboot.repository.UserRepository;

@Configuration
public class SpringSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	return http.authorizeHttpRequests(auth -> {
	    auth.requestMatchers("/").permitAll().requestMatchers("/login", "/login**", "/css/**", "/js/**",

		    "/forgot-password", "/reset-password", "/reset-success", "/register").permitAll()
		    .requestMatchers("/admin").hasRole("ADMIN").requestMatchers("/user").hasRole("MEMBER").anyRequest()
		    .authenticated();
	}).formLogin((form) -> form
		.loginPage("/login").usernameParameter("login").failureUrl("/login?loginError=true").permitAll())
		.logout((logout) -> logout.logoutSuccessUrl("/login?logoutSuccess=true").permitAll())
		.exceptionHandling(exception -> exception
			.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login?loginRequired=true")))
		.build();
    }

    // test pk mdp bob fct pas
    @Bean
    CommandLineRunner checkBob(PasswordEncoder pe, UserRepository repo) {
	return args -> {
	    User bob = repo.findByLogin("bob");
	    System.out.println("hash=" + bob.getPassword());
	    System.out.println("matches(bob,bobHash)=" + pe.matches("bob", bob.getPassword()));
	};
    }
}