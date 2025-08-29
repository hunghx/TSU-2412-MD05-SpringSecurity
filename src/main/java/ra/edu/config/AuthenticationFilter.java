package ra.edu.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // kiểm tra request
        authentication(request);

        // gửi request đi ttiếp
        filterChain.doFilter(request,response);
    }

    private void authentication(HttpServletRequest request){
        String authorization = request.getHeader("Authorization"); // lấy username và pass theo dạng base-64
        // giải mã base-64
        if(authorization!=null && authorization.startsWith("Basic ")){ // httpBasic
            // cắt chuối
            String base64Credentials = authorization.substring("Basic ".length()).trim();
            System.out.println("credentials : "+base64Credentials);

            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentialsStr = new String(credDecoded, StandardCharsets.UTF_8); // hunghx:123456
            System.out.println("credential :"+credentialsStr);

            String[] arrStr = credentialsStr.split(":");
            String username = arrStr[0];
            String password = arrStr[1];
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                // lưu vao
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                Authentication token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(token);
                System.out.println("role"+ token.getAuthorities());

            }catch (AuthenticationException e){
                e.printStackTrace();
            }
        }
    }
}
