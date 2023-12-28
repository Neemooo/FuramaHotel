package com.example.demo2608;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Properties;

@SpringBootApplication
public class Demo2608Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2608Application.class, args);
	}

	@Autowired

	public BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("user").password(passwordEncoder.encode("password")).roles("user")
				.and()
				.withUser("admin").password(passwordEncoder.encode("admin")).roles("admin");
	}

//
//	private AuthenticationManager authenticationManager;
//
//    @Autowired
//    public void RegistrationService(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

}
