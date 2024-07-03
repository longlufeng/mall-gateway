package com.llf.filters;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSONObject;
import com.llf.enums.ResultCodeEnum;
import com.llf.utils.Result;
import com.llf.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 校验会话
 */
@Component
@Slf4j
public class SessChkGlobalFilter implements GlobalFilter, Ordered {@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		return chain.filter(exchange);
	}

//	@Autowired
//	private RedisTemplate<String, Object> redisTemplate;
//
//	@Value("${sess.time-out}")
//	public String sessTimeOut;
//
//	@Value("${url.no-need-chk-session}")
//	public String noNeedChkSession;
//
//	@Override
//	public int getOrder() {
//		return 0;
//	}
//
//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//		
//		log.info("进入SessChkGlobalFilter");
//
//		ServerHttpRequest request = exchange.getRequest();
//		String path = request.getURI().getPath();
//		log.info("path:{}", path);
//		if (noNeedChkSession.indexOf(path) > -1) {
//			return chain.filter(exchange);
//		}
//		
//		AtomicInteger atoUserId = new AtomicInteger();
//		AtomicInteger atoSessionId = new AtomicInteger();
//		exchange.getSession().subscribe(session -> {
//            String userId = session.getAttribute("userId");
//            log.info("会话获取的userId为1：");
//            String sessionId = session.getId();
//            atoUserId.set(Integer.parseInt(userId));
//            atoSessionId.set(Integer.parseInt(sessionId));
//        });
//		
//		String userId =  String.valueOf(atoUserId);
//		String sessionId =  String.valueOf(atoSessionId);
//		
//		Object RedisSssionId = redisTemplate.opsForValue().get("userId:"+userId);
//		if(StringUtil.isEmpty(sessionId) || StringUtil.isEmpty(RedisSssionId)) {
//			 return out(exchange.getResponse(), ResultCodeEnum.SESS_TIME_OUT);
//		}
//		
//		if(!sessionId.equals(RedisSssionId)) {
//			 return out(exchange.getResponse(), ResultCodeEnum.USER_TIP);
//		}
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

}
