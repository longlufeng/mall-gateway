package com.llf.filters;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSONObject;
import com.llf.enums.ResultCodeEnum;
import com.llf.utils.Result;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * token校验
 * 1、从请求头获取token
 * 2、通过token从redis获取userId，不为空则认证通过，否则为未登录
 */
//@Component
//@Slf4j
//public class AuthChkGlobalFilter implements GlobalFilter, Ordered {
//	
//	private static final String AUTHORIZE_TOKEN = "token";
//
//	@Autowired
//	private RedisTemplate<String, Object> redisTemplate;
//
//	@Value("${sess.time-out}")
//	public String sessTimeOut;
//
//	@Value("${url.no-need-chk-login}")
//	public String noNeddChkLogin;
//
//	@Override
//	public int getOrder() {
//		return 1;
//	}
//
//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//		
//		log.info("进入AuthChkGlobalFilter");
//		
//        ServerHttpRequest request = exchange.getRequest();
//        String path = request.getURI().getPath();
//		log.info("path:{}", path);
//		if (noNeddChkLogin.indexOf(path) > -1) {
//			return chain.filter(exchange);
//		}
//		
//		HttpHeaders headers = request.getHeaders();
//		String token = headers.getFirst(AUTHORIZE_TOKEN);
//		if (token == null){
//            log.error("token为空");
//            return out(exchange.getResponse(), ResultCodeEnum.LOGIN_AUTH);
//        }
//        
//		Object userId = redisTemplate.opsForValue().get(token);
//		if (userId == null || userId.equals("")){
//            log.error("redis不存在此token");
//            return out(exchange.getResponse(), ResultCodeEnum.LOGIN_AUTH);
//        }
//		
//        return chain.filter(exchange);
//	}
//	
//	/**
//     * api接口鉴权失败返回数据
//     * @param response
//     * @return
//     */
//    private Mono<Void> out(ServerHttpResponse response, ResultCodeEnum resultCodeEnum) {
//    	Result<?> result = Result.build (null, resultCodeEnum);
//        byte[] bits = JSONObject.toJSONString(result).getBytes(StandardCharsets.UTF_8);
//        DataBuffer buffer = response.bufferFactory().wrap(bits);
//        //指定编码，否则在浏览器中会中文乱码
//        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//        return response.writeWith(Mono.just(buffer));
//    }
//
//}
