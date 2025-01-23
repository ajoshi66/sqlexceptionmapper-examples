package com.joshi.manufacturer.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ManufacturerControllerTest {
	
	@LocalServerPort
	private int localPort;
	
//	private final String url = "http://localhost:" + localPort + "/manufacturers";
	
	@Autowired
	private TestRestTemplate trt;

	@Test
	void testIsRestTemplateActive() {
		log.debug("Checking Rest Template...");
		assertThat(trt).isNotNull();
	}

	@Test
	void testIsWebServerAccessible() {
		String url = "http://localhost:" + localPort + "/manufacturers";
		log.debug("testIsWebServerAccessible::url::" + url);
		String resp = trt.getForObject(url, String.class);
		log.debug("testIsWebServerAccessible::response::" + resp);
		assertThat(resp).contains("\"id\":");
	}
	
	@Test
	void testGetManufacturer() {
		int mnfrId = 1;
		String url = "http://localhost:" + localPort + "/manufacturers/" + mnfrId;
		log.debug("testGetManufacturer::url::" + url);
		String resp = trt.getForObject(url, String.class);
		log.debug("testGetManufacturer::response::" + resp);
		assertThat(resp).contains("\"id\":" + mnfrId);
	}

	@Test
	void testPatchManufacturer() {
		int mnfrId = 1;
		String resp;
		String url = "http://localhost:" + localPort + "/manufacturers/" + mnfrId;
		String testPatch = "{\"address2\": \"Basavanagudi\"}";
		log.debug("testPatchManufacturer::url::" + url);
		resp = trt.patchForObject(url, testPatch, String.class);
		log.debug("testPatchManufacturer::Patch response::" + resp);
		resp = trt.getForObject(url, String.class);
		log.debug("testPatchManufacturer::Get response::" + resp);
		assertThat(resp).contains("\"address2\":\"Basavanagudi\"");
	}

}
