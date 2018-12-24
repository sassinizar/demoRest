package org.ns;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.ns.dao.ContactRepository;
import org.ns.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoRestApplication implements CommandLineRunner{
	
	@Autowired
	private ContactRepository contactRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		contactRepo.save(new Contact("sassi", "nizar",df.parse("27/02/1991"), "sassi_nizar@outlook.fr", 28393579, "C:\\Users\\Nizar Sassi\\Pictures\\photos"));
		contactRepo.save(new Contact("ali", "dridi",df.parse("27/02/1989"), "ali_dridi@outlook.fr", 24893577, "C:\\Users\\Nizar Sassi\\Pictures\\photos"));
		contactRepo.save(new Contact("med", "ali",df.parse("27/12/1991"), "nizar@outlook.fr", 28787588, "C:\\Users\\Nizar Sassi\\Pictures\\photos"));
		contactRepo.save(new Contact("karim", "rahoui",df.parse("01/02/1992"), "karim_nizar@outlook.fr", 28784152, "C:\\Users\\Nizar Sassi\\Pictures\\photos"));
	
		contactRepo.findAll().forEach(c->{
			System.out.print(c.getNom());
		});
	}
}
