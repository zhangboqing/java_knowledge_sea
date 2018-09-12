package com.zbq.springiocv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringIocV1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringIocV1Application.class, args);
	}

}
