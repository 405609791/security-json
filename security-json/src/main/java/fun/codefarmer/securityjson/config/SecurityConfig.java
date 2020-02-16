package fun.codefarmer.securityjson.config;

import fun.codefarmer.securityjson.filter.MyAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @ @ClassName SecurityConfig
 * @ Descriotion TODO
 * @ Author admin
 * @ Date 2020/2/16 17:01
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and().csrf().disable();
        //filter加到什么位置 :
        http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    MyAuthenticationFilter myAuthenticationFilter() throws Exception {

        MyAuthenticationFilter filter = new MyAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
}
