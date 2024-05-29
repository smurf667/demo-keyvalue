package com.example.demo;

import java.util.Iterator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.keyvalue.core.query.KeyValueQuery;
import org.springframework.data.map.repository.config.EnableMapRepositories;

@SpringBootApplication
@EnableMapRepositories
public class DemoApplication {

	@Bean
	Runnable demo(EmployeeRepository repository, KeyValueTemplate keyValueTemplate) {
		return () -> {
			// create demo employee
			Employee employee = new Employee(1, "Demo");
			repository.save(employee);
			// use KeyValueTemplate
			Iterator<Employee> employees = keyValueTemplate
				.find(new KeyValueQuery<>("name.contains('mo') || name.contains('De')"), Employee.class)
				.iterator();
			if (employees.hasNext()) {
				System.out.println("found the following employees:");
				while (employees.hasNext()) {
					System.out.printf("  - %s%n", employees.next());
				}
			}
		};
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		context.getBean("demo", Runnable.class).run();
	}

}
