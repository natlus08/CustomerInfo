/**
 * 
 */
package com.info.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author 387090
 *
 */
@SpringBootApplication(scanBasePackages = { "com.info"})
@EnableMongoRepositories("com.info.repository")
public class CustomerInfoBoot {

	/**
	 * Acts as the launch point for the entire application
	 */
	public static void main(String[] args) {
		SpringApplication.run(CustomerInfoBoot.class, args);

	}

}
