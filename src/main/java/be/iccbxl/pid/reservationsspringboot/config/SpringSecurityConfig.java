package be.iccbxl.pid.reservationsspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

	return http.csrf(csrf -> csrf.disable())

		.authorizeHttpRequests(auth -> {
		    auth.requestMatchers("/", "/login", "/login**", "/css/**", "/js/**", "/forgot-password",
			    "/reset-password", "/reset-success", "/register").permitAll();

		    // API publique
		    auth.requestMatchers("/api/artists", "/api/artists/**").permitAll();

		    // API réservée aux administrateurs
		    auth.requestMatchers("/api/admin/**").hasRole("ADMIN");

		    auth.requestMatchers("/admin").hasRole("ADMIN");
		    auth.requestMatchers("/user").hasRole("MEMBER");

		    auth.anyRequest().authenticated();
		})

		.httpBasic(Customizer.withDefaults())

		.formLogin(form -> form.loginPage("/login").usernameParameter("login")
			.failureUrl("/login?loginError=true").permitAll())

		.logout(logout -> logout.logoutSuccessUrl("/login?logoutSuccess=true").permitAll())

		.exceptionHandling(exception -> exception
			.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login?loginRequired=true")))

		.build();
    }
}