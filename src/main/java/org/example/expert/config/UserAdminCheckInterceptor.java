package org.example.expert.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.user.enums.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class UserAdminCheckInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    private final Logger logger = Logger.getLogger("Interceptor API logging");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String bearerJwt = request.getHeader("Authorization");
        String jwt = jwtUtil.substringToken(bearerJwt);

        Claims claims = jwtUtil.extractClaims(jwt);
        UserRole userRole = UserRole.valueOf(claims.get("userRole", String.class));

        if (!userRole.equals(UserRole.ADMIN)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "접근 권한이 없습니다.");
            return false;
        }

        String path = request.getRequestURI();
        String decodePath = URLDecoder.decode(path, StandardCharsets.UTF_8);

        logger.info("요청 URL: " + decodePath);
        logger.info("요청 시각: " + LocalDateTime.now());

        return true;
    }
}