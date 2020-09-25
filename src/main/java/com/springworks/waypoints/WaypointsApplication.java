package com.springworks.waypoints;

import com.springworks.waypoints.categoritazion.Categorizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WaypointsApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory
			.getLogger(WaypointsApplication.class);

	public static void main(String[] args) {
		SpringApplication
				.run(WaypointsApplication.class, args);
	}
	@Override
	public void run(String... args) {
		if (args.length != 1) {
			System.out.println("Waypoints: Not enough arguments.");
			System.out.println("Usage: mvn spring-boot:run -q -Dspring-boot.run.arguments=<waypoints.json>");
		} else {
			Categorizer categorizer = new Categorizer( args[0] );
			categorizer.printResult();
		}

	}

}
