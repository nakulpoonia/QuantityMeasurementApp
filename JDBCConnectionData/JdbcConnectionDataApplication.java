package com.example.JDBCConnectionData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JdbcConnectionDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcConnectionDataApplication.class, args);
		QuantityMeasurementRepository repo = new QuantityMeasurementRepository();


		repo.save(new QuantityMeasurementEntity("ADD", 30));
		repo.save(new QuantityMeasurementEntity("COMPARE", 1));


		System.out.println("All Data:");
		List<QuantityMeasurementEntity> data = repo.getAll();

		for (QuantityMeasurementEntity e : data) {
			System.out.println(e);
		}


		System.out.println("Total Count: " + repo.count());


		repo.deleteAll();

		System.out.println("After delete count: " + repo.count());
	}

}
