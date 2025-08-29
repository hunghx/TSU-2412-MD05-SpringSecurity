package ra.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
    // mã hóa mật khẩu
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Xác thực thông qua username : UserDetailService
   // Khai báo các tài khoản mac định
//    @Bean // user - hashpassword đc tự sinh khi start ứng dụng
//    public UserDetailsService  userDetailsService(){
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder()
//                        .encode("123456"))
//                .roles("ADMIN").build();
//        UserDetails user = User.withUsername("hunghx")
//                .password(passwordEncoder()
//                        .encode("hunghx123"))
//                .roles("USER").build();
//        return new InMemoryUserDetailsManager(admin,user);
//    }

    @Autowired
    private UserDetailsService userDetailsService; //  tiêm userDeatailService custom vào theo logic  đã triển khai

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

}
