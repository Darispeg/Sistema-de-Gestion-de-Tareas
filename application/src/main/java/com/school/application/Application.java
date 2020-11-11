package com.school.application;

import javax.annotation.Resource;

import com.school.application.repository.Tarea.service.FileService;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired
	private Environment environment;

	@Resource
	private FileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
/* 		fileService.init(); */
		LogFactory.getLog(getClass()).info(environment.getProperty("spring.datasource.data-username"));
	}
}
