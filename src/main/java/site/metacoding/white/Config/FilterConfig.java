package site.metacoding.white.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.white.config.auth.JwtAuthenticationFilter;
import site.metacoding.white.config.auth.JwtAuthorizationFilter;
import site.metacoding.white.domain.UserRepository;

@Slf4j
@RequiredArgsConstructor
@Configuration // ioc 컨테이너 등록
public class FilterConfig {

    private final UserRepository userRepository;// DI (스프링 IOC 컨테이너에 들어옴)

    @Bean // ioc 컨테이너 등록, 서버 실행시
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilterRegister() {
        log.debug("디버그 : 인증 필터 실행");

        FilterRegistrationBean<JwtAuthenticationFilter> bean = new FilterRegistrationBean<JwtAuthenticationFilter>(
                new JwtAuthenticationFilter(userRepository));// 필터 등록
        bean.addUrlPatterns("/login");// 언제 필터 발동할지
        bean.setOrder(1);// 낮은 순서대로 실행
        return bean;

    }

    @Profile("dev")
    @Bean // ioc 컨테이너 등록, 서버 실행시
    public FilterRegistrationBean<JwtAuthorizationFilter> jwtAuthorizationFilterRegister() {
        log.debug("디버그 : 인가 필터 실행");

        FilterRegistrationBean<JwtAuthorizationFilter> bean = new FilterRegistrationBean<JwtAuthorizationFilter>(
                new JwtAuthorizationFilter());// 필터 등록
        bean.addUrlPatterns("/s/*");// 원래 두개인데 이친구만 하나임
        bean.setOrder(2);
        return bean;
    }
}
