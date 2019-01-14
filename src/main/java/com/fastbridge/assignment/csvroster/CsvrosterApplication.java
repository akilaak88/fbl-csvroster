package com.fastbridge.assignment.csvroster;

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

		SpringApplication.run(CsvrosterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		if(args.length==2)
		{
		MainController mainController = appContext.getBean("mainController", MainController.class);

		mainController.readCSV(args[0], args[1]);
		} else
			System.out.println("Please enter the Path for the CSV File and (Y/N) to indicate if it is a delta import.");
		System.exit(0);

	}

}
