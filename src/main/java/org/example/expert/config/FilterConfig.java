package org.example.expert.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter(jwtUtil, objectMapper));
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<HttpServletWrappingFilter> firstFilterRegister()  {
        FilterRegistrationBean<HttpServletWrappingFilter> registrationBean =
                new FilterRegistrationBean<>(new HttpServletWrappingFilter());
        registrationBean.setOrder(Integer.MIN_VALUE);

        return registrationBean;
    }
}
