package fun.codefarmer.securityjson.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

/**
 * 1/继承 UsernamePasswordAuthenticationFilter 重写 attemptAuthentication
 *   虽然是重写，增强了attemptAuthentication方法，之前只支持key-value 形式，现在加入了 json 格式
 * 2/如何让这个类生效
 * @ @ClassName MyAuthenticationFilter
 * @ Descriotion TODO
 * @ Author admin
 * @ Date 2020/2/16 16:15
 **/
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //判断请求数据的类型
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            //说明用户是以json的形式传递的阐述
            String username = null;
            String password = null;
            try {
                //Java的Jackson库中ObjectMapper类的使用,Jackson库通常被用来实现Java的对象和JSON之间的转换功能
                //ObjectMapper类是Jackson库的主要类。它提供一些功能将转换成Java对象匹配JSON结构
                Map<String,String> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                username = map.get("username");
                password = map.get("password");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }

            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);

        }
        return super.attemptAuthentication(request, response);
    }
}
