package razzle.ai;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
	classes = {
		RazzleAutoConfiguration.class,
	}
)
class RazzleSdkAutoconfigurationTests {

	@Test
	void contextLoads() throws Exception {
		Thread.sleep(6000000);
	}

}

