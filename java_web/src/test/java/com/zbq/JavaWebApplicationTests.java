package com.zbq;

import com.zbq.controller.spring.retry.MyRetryTestByAnnotation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaWebApplicationTests {

	@Autowired
	private MyRetryTestByAnnotation myRetryTestByAnnotation;

//	@Test
//	public void contextLoads() {
//	}


	@Test
	public void run1() throws Exception {
		myRetryTestByAnnotation.service();
	}
}
