package com.charsy.spring.boot.docker.demo1.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SmsCorsFilter extends CorsFilter {
/**
 * This class handles the response to client, so that
 * AllowOrigin at appended to the headers before response.
 * This require HttpSecurity to apply HttpSecurity.cors()	
 * Otherwise during login we will encounter Preflight login exception
 */
	public SmsCorsFilter() {
		super(SmsCorsFilter.configurationSource());
	}
	
    private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // origins
        config.addAllowedOrigin("*");

        // when using ajax: withCredentials: true, we require exact origin match
        config.setAllowCredentials(true);

        // headers
        config.addAllowedHeader("*");
//        config.addAllowedHeader("x-requested-with");

        // methods
        config.addAllowedMethod(HttpMethod.OPTIONS);
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.DELETE);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // source.registerCorsConfiguration("/applogin", config);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

