package africa.semicolon.lumexpress.security.filters;

import africa.semicolon.lumexpress.data.models.LumExpressUser;
import africa.semicolon.lumexpress.security.managers.CustomAuthenticationManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class LumExpressUserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final CustomAuthenticationManager customAuthenticationManager;
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)  {

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            LumExpressUser user = objectMapper.readValue(request.getInputStream(), LumExpressUser.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(email, password);
        customAuthenticationManager.authenticate(token);
        if(token!=null){
          SecurityContext context =  SecurityContextHolder.getContext();
           context.setAuthentication(token);
            return token;
        }

        throw new BadCredentialsException("Bad Credentials");

    }

}
