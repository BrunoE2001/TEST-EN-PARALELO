package org.skyline.example.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

//@SpringBootTest
//@TestPropertySource(locations = {"classpath:/junit-platform.properties"})
class ExampleTestingApplicationTests {

	@Test
	@Execution(ExecutionMode.CONCURRENT)
	public void first() throws Exception{
		System.out.println("FirstParallelUnitTest first() start => " + Thread.currentThread().getName());
		Thread.sleep(500);
		System.out.println("FirstParallelUnitTest first() end => " + Thread.currentThread().getName());
	}

	@Test
	public void second() throws Exception{
		System.out.println("FirstParallelUnitTest second() start => " + Thread.currentThread().getName());
		Thread.sleep(500);
		System.out.println("FirstParallelUnitTest second() end => " + Thread.currentThread().getName());
	}

}
