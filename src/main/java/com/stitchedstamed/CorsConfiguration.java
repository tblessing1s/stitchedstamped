package com.stitchedstamed;

import com.stitchedstamed.models.Monogram;
import com.stitchedstamed.models.PurchaseOrder;
import com.stitchedstamed.models.SpecialOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class CorsConfiguration implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.getCorsRegistry()
                .addMapping("/**")
                .allowedMethods("GET", "POST", "OPTIONS", "PATCH", "DELETE")
                .allowedOrigins("*");

        config.exposeIdsFor(PurchaseOrder.class, Monogram.class, SpecialOrder.class);
    }
}
