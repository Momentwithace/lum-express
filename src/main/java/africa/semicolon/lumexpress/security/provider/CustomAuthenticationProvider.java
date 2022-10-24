package africa.semicolon.lumexpress.security.provider;


import africa.semicolon.lumexpress.security.CustomUserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       UserDetails userDetails = customUserDetailsService.loadUserByUsername((String)authentication.getPrincipal());
      if(passwordEncoder.matches((String) authentication.getCredentials(), userDetails.getPassword())){
          return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                  authentication.getCredentials(), userDetails.getAuthorities());
        }
      throw new AuthenticationCredentialsNotFoundException("Email does not exist");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        var appAuthType
                = UsernamePasswordAuthenticationToken.class;
        return authentication.equals(appAuthType);
    }
}
