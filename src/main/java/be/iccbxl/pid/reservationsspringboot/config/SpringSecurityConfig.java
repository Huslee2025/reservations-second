package be.iccbxl.pid.reservationsspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import be.iccbxl.pid.reservationsspringboot.service.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
			throws Exception {
		AuthenticationManagerBuilder authMngrBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authMngrBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);

		return authMngrBuilder.build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/", "/login", "/logout", "/css/**", "/js/**").permitAll();
			auth.requestMatchers("/admin").hasRole("ADMIN");
			auth.requestMatchers("/user").hasRole("USER");
			auth.anyRequest().authenticated();
		})

				.formLogin(form -> form.loginPage("/login").usernameParameter("login")
						.failureUrl("/login?loginError=true"))
				.logout(logout -> logout.logoutSuccessUrl("/login?logoutSuccess=true").deleteCookies("JSESSIONID"))
				.exceptionHandling(exception -> exception
						.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login?loginRequired=true")))
				.build();
	}

}
