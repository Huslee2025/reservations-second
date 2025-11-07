package be.iccbxl.pid.reservationsspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import be.iccbxl.pid.reservationsspringboot.service.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(BCryptVersion.$2Y, 12);
	}

	@Bean
	public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http
				// CSRF activé par défaut.
				// .csrf(csrf -> csrf.withDefaults())

				.authorizeHttpRequests(auth -> auth
						// Public
						.requestMatchers("/", "/login", "/login/**", "/css/**", "/js/**", "/forgot-password",
								"/reset-password", "/reset-success")
						.permitAll()
						// Protégé par rôles
						.requestMatchers("/admin").hasRole("ADMIN").requestMatchers("/user").hasRole("MEMBER")
						// Le reste demande une authentification
						.anyRequest().authenticated())

				.formLogin(form -> form.loginPage("/login").usernameParameter("login")
						.failureUrl("/login?loginError=true").permitAll())

				.logout(logout -> logout.logoutSuccessUrl("/login?logoutSuccess=true").deleteCookies("JSESSIONID")
						.permitAll())

				.exceptionHandling(ex -> ex
						.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login?loginRequired=true")))

				.build();
	}

}
