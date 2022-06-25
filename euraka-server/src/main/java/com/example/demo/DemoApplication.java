package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

import java.util.Map;
import java.util.stream.Collectors;

@EnableEurekaServer
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private ConfigurableEnvironment configurableEnvironment;


	@Override
	public void run(String... args) throws Exception {
		Map<String, Object> mapOfProperties = configurableEnvironment.getPropertySources()
				.stream()
				.filter(propertySource -> propertySource.getName()
						.contains(".properties"))
				.collect(Collectors.toMap(PropertySource::getName, PropertySource::getSource));
		mapOfProperties.values()
				.forEach(System.out::println);
	}

}
