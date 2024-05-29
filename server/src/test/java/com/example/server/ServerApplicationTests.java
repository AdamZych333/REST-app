package com.example.server;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.server.service.NbpExchangeratesService;
import com.example.server.service.RequestLogService;

@SpringBootTest
class ServerApplicationTests {

	@Autowired
	private RequestLogService requestLogService;

	@Autowired
	private NbpExchangeratesService nbpExchangeratesService;

	@Test
	void contextLoads() {
		assertNotNull(requestLogService);
		assertNotNull(nbpExchangeratesService);
	}

}
