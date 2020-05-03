package com.srbarrios.mybackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.srbarrios.mybackend.entities.User;

@SpringBootApplication
@EnableRedisRepositories
@ComponentScan
@Configuration
public class MyBackendApplication {

	@Value(value = "${redis.hostname}")
	private String redisHostname;

	@Value(value = "${redis.port}")
	private int redisPort;

	@Value(value = "${redis.password}")
	private String redisPassword;

	public static void main(final String[] args) {
		SpringApplication.run(MyBackendApplication.class, args);
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHostname, redisPort);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPassword));
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean
	public RedisTemplate<String, User> userTemplate() {
		RedisTemplate<String, User> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		return template;
	}
}
