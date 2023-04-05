package com.microservices.io;

import com.microservices.io.model.Inventory;
import com.microservices.io.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceApplication {
	private final InventoryRepository inventoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(){
		return args -> {
			log.info("Saving inventory data");
			List<Inventory> inventoryList = new ArrayList<>();
			inventoryList.add(getInventory("111", 100));
			inventoryList.add(getInventory("222", 50));
			inventoryList.add(getInventory("333", 150));
			inventoryRepository.saveAll(inventoryList);
			log.info("Inventory Data loaded successfully.");
		};
	}

	private static Inventory getInventory(String skuCode, int quantity) {
		return Inventory
				.builder()
				.skuCode(skuCode)
				.quantity(quantity)
				.isActive(Boolean.TRUE)
				.build();
	}

}
