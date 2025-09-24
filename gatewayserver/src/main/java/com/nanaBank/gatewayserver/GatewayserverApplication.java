package com.nanaBank.gatewayserver;

import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;


@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}
	
	//bean to configure custom routes with filters for our services on startup
	@Bean
	RouteLocator NanaBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder){
	
		return routeLocatorBuilder.routes()
				.route(p -> p.path("/nanabank/accounts/**")
						.filters(f -> f.rewritePath("/nanabank/accounts/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time",LocalDateTime.now().toString())
								.circuitBreaker(Config -> Config.setName("accountsCircuitBreaker")
										.setFallbackUri("forward:/contactSupport")))
						.uri("lb://ACCOUNTS"))
				
				.route(p -> p.path("/nanabank/loans/**")
						.filters(f -> f.rewritePath("/nanabank/loans/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time",LocalDateTime.now().toString()
										).retry(retryConfig -> retryConfig.setRetries(3)
												.setMethods(HttpMethod.GET)
												.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)))
												
						.uri("lb://LOANS"))
				
				.route(p -> p.path("/nanabank/cards/**")
						.filters(f -> f.rewritePath("/nanabank/cards/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time",LocalDateTime.now().toString()))
						.uri("lb://CARDS")).build();
	}
	
	/**
	 * @Bean
	 * to override the default timelimit of circuit breaker (1)
	 * so that it works to the set customised time (connection and response timeout)
	 * its set to 4 seconds
	 */
	@Bean
	 Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer(){
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom()
						.timeoutDuration(Duration.ofSeconds(4))
						.build())
				.build());
		
	}
}
