package com.secondhand.secondhand.config;

import com.secondhand.secondhand.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;


/**
 * Config for login/logout/register and other URL request filters
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/register").permitAll()
                .antMatchers(HttpMethod.POST, "/admin/register").permitAll()
                .antMatchers(HttpMethod.POST, "/user/authenticate").permitAll()
                .antMatchers(HttpMethod.POST, "/admin/authenticate").permitAll()
                .antMatchers(HttpMethod.GET, "/user/search/*").permitAll()

                //Review Add
                .antMatchers(HttpMethod.POST, "/*/reviews").permitAll()
                .antMatchers(HttpMethod.GET, "/reviews").permitAll()
                //.antMatchers(HttpMethod.GET, "/reviews").permitAll()
                .antMatchers(HttpMethod.POST, "/reviews").permitAll()

                // Address Upload
                .antMatchers(HttpMethod.POST, "/addresses").hasAuthority("ROLE_USER")
                // Only For test
                .antMatchers(HttpMethod.GET, "/search/addresses").permitAll()

                // Genre Add @PostMapping("/genres")
                // For Initialization
                .antMatchers(HttpMethod.POST, "/genres").permitAll()

                // Get Product By Genre
                .antMatchers(HttpMethod.GET, "/products/genres").permitAll()

                // Product Add
                .antMatchers(HttpMethod.POST,"/products").hasAuthority("ROLE_USER")
                // Product Get my products
                .antMatchers(HttpMethod.GET,"/products").hasAuthority("ROLE_USER")

                // Product Search By Filter
                .antMatchers(HttpMethod.GET,"/filters").permitAll()

                // Favorite
                .antMatchers(HttpMethod.POST,"/favorite").hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.GET,"/favorite").hasAuthority("ROLE_USER")

                .antMatchers("/user/getinfo").hasAuthority("ROLE_USER")
                .antMatchers("/user/update").hasAuthority("ROLE_USER")
                .antMatchers("/user/delete").hasAuthority("ROLE_USER")
                .antMatchers("/order/create").hasAuthority("ROLE_USER")
                .antMatchers("/order/update").hasAuthority("ROLE_USER")
                .antMatchers("/order/getbybuyer").hasAuthority("ROLE_USER")
                .antMatchers("/order/getbyseller").hasAuthority("ROLE_USER")
                .antMatchers("/order/getbyorder/*").hasAuthority("ROLE_USER")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authority WHERE username = ?");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
