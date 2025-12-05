package org.example.expert.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import java.time.LocalDateTime;

@Aspect
public class LoggingAspect {
    private final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(org.example.expert.aop.LoggingAdmin)")
    public void LoggingAdminPointcut() {
    }

    @Around("LoggingAdminPointcut()")
    public Object LoggingAdminAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        ObjectMapper objectMapper = new ObjectMapper();

        ContentCachingRequestWrapper requestWrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        String body = requestWrapper.getContentAsString();

        log.info("요청한 사용자 ID: {}", request.getAttribute("userId"));
        log.info("요청 본문: {}", body);
        log.info("API 요청 시각: {}", LocalDateTime.now());
        log.info("API 요청 URL: {}", request.getRequestURI());

        Object result = joinPoint.proceed();
        String resultBody = objectMapper.writeValueAsString(result);

        if (result != null) {
            log.info("응답 본문: {}", resultBody);
        }

        return result;
    }
}