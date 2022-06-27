package com.ysferdgnn.postapp_api;

import com.ysferdgnn.postapp_api.api.database.repos.concretes.BaseRepositoryFactoryBean;
import com.ysferdgnn.postapp_api.api.database.repos.concretes.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
@EnableJpaRepositories(
		basePackages = {
				"com.ysferdgnn.postapp_api.api.database.repos.concretes"
		},
		basePackageClasses = BaseRepositoryImpl.class,
		repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class
)
@EntityScan(basePackages = "com.ysferdgnn.postapp_api.api.database.models")
public class PostappApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostappApiApplication.class, args);
	}

}
