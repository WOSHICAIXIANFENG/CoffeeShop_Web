package edu.mum.coffee.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	.antMatchers("/","/index", "/productlist", "/register").permitAll()
            	.antMatchers("/createProduct", "/editProduct", "/removePerson", "/addPerson").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
            	.permitAll()
            	.and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.logoutSuccessUrl("/")
                .permitAll();
    }

	@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final Properties users = new Properties();
        //users.put("user","pass,ROLE_USER,enabled"); //add whatever other user you need
        return new InMemoryUserDetailsManager(users);
    }
	
	@Autowired
	private InMemoryUserDetailsManager manager;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		SimpleGrantedAuthority authAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");
		SimpleGrantedAuthority authUser = new SimpleGrantedAuthority("ROLE_USER");
		List<SimpleGrantedAuthority> auths1 = new ArrayList<>();
		auths1.add(authAdmin);
		
		List<SimpleGrantedAuthority> auths2 = new ArrayList<>();
		auths2.add(authUser);
		
		manager.createUser(new User("super", "pw", auths1));
		manager.createUser(new User("xianfeng", "pw", auths1));
		manager.createUser(new User("samuel", "pw", auths2));
		
//		auth.inMemoryAuthentication().withUser("super").password("pw").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("xianfeng").password("pw").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("samuel").password("pw").roles("USER");
	}
}