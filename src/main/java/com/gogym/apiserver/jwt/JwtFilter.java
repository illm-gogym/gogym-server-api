package com.gogym.apiserver.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogym.apiserver.error.common.ErrorCode;
import com.gogym.apiserver.error.exception.ExpiredTokenException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter implements Filter {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;

    // 실제 필터링 로직은 doFilter 내부에 작성 jwt 토큰의 인증 정보를 SecurityContext에 저장하는 역할
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // request에서 jwt 토큰 정보 추출
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        // token 유효성 검증에 통과하면
        try {
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                Authentication authentication = tokenProvider.getAuthentication(jwt); // 정상 토큰이면 SecurityContext 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
            }
            chain.doFilter(request, response);
        } catch (ExpiredTokenException e) {
            setErrorResponse(httpServletRequest, httpServletResponse, e);
        }
//        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
//            Authentication authentication = tokenProvider.getAuthentication(jwt); // 정상 토큰이면 SecurityContext 저장
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
//        } else {
//            request.setAttribute("invalid", ErrorCode.TOKEN_INVALID);
//            log.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
//        }


    }

    private void setErrorResponse(HttpServletRequest request, HttpServletResponse response, ExpiredTokenException ex) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        final Map<String, Object> body = new HashMap<>();
        body.put("status", ex.getErrorCode().getHttpStatus());
        body.put("error", ex.getErrorCode().getErrorCode());
        body.put("message", ex.getErrorCode().getMessage());
        body.put("path", request.getServletPath());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
        response.setStatus(HttpServletResponse.SC_OK);

    }

    // request header에서 토큰 정보를 꺼내오는 메소드
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
