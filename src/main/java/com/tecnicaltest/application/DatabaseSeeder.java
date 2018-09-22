package com.tecnicaltest.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tecnicaltest.model.Customer;
import com.tecnicaltest.repository.CustomerRepository;
import com.tecnicaltest.repository.InvoiceRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	
	public DatabaseSeeder() {
		super();
	}
	
	
	@Override
	public void run(String... arg0) throws Exception {
		 /*
		  *  Llenar la BD de menoria con usuario de prueba
		  * */
		 //2 usuarios

		Customer c = new Customer(null, "Israel", "Contreras", "79897158", "Cra 82 Num 6-11 Bogotá","Ingeniero Sist");

		customerRepository.save(c);
		c = new Customer(null, "Maria", "González", "11344455", "Calle 80 #22-99 Bogotá","Abogada");
		customerRepository.save(c);
		
		Iterable<Customer> customers = customerRepository.findAll();
		for (Customer cc : customers) {
			System.out.println("Usuario cargado Id= " +  cc.getId() + ", Nombre: " + cc.getFirstName() + " " + cc.getLastName() + "");
		}
	}

}
