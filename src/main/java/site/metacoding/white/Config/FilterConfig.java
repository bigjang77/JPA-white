package site.metacoding.white.Config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration // ioc 컨테이너 등록
public class FilterConfig {

    @Bean // ioc 컨테이너 등록, 서버 실행시
    public FilterRegistrationBean<HelloFilter> jwtAuthenticationFilterRegister() {
        log.debug("디버그 : 인증 필터 실행");
        FilterRegistrationBean<HelloFilter> bean = new FilterRegistrationBean<HelloFilter>(new HelloFilter());// 필터 등록
        bean.addUrlPatterns("/hello");// 언제 필터 발동할지

        return bean;

    }
}

@Slf4j
class HelloFilter implements Filter { // 연습 필터 생성

    // /hello 요청시
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 다운캐스팅
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getMethod().equals("POST")) {
            log.debug("디버그 : HelloFilter 실행됨");
        } else {
            log.debug("디버그 : POST요청이 아니라서 실행할 수 없습니다");
        }

        // chain.doFilter(req, resp);
    }

}