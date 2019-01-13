package com.fastbridge.assignment.csvroster;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.fastbridge.assignment.csvroster.controller.MainController;

@SpringBootApplication
public class CsvrosterApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext appContext;

	public static void main(String[] args) {

		if(args.length==2) {
		System.setProperty("path", args[0]);
		System.setProperty("isDelta", args[1]);
		SpringApplication.run(CsvrosterApplication.class, args);
		} else
			System.out.println("Please enter a Valid Path of the CSV File and a flag(Y/N) to indicate whether it is a delta import.");
	}

	@Override
	public void run(String... args) throws Exception {

		MainController mainController =  appContext.getBean("mainController",MainController.class);

		mainController.readCSV();
		
		System.exit(0);

	}

}
