package com.shopping.esoshop;



// import java.time.LocalDateTime;
// import java.util.Date;
// import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.shopping.esoshop.model.Customer;

import com.shopping.esoshop.model.*;
import com.shopping.esoshop.service.DaoService;


@SpringBootApplication
public class EsoshopApplication implements CommandLineRunner {

	@Autowired 
	private DaoService daoService;
	
	public static void main(String[] args) {
		SpringApplication.run(EsoshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// System.out.println(dao.createOrder(1));
		// System.out.println(daoService.getPage(2));
		// System.out.println(daoService.getAllProduct());
		// Customer customer = daoService.getCustomerById(1);
		// System.out.println(daoService.payBill(daoService.getBillOfCustomer(customer,"120231005T010704072483900")));
		// System.out.println(daoService.getAllProduct());
	}
	
}
