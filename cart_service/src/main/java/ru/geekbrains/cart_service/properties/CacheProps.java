package ru.geekbrains.cart_service.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.cache.user")
@Data
public class CacheProps {

    private Long expireTime;

    private String name;

    private String defaultValue;
}
