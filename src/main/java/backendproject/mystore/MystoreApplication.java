package backendproject.mystore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backendproject.mystore.domain.AppUser;
import backendproject.mystore.domain.AppUserRepository;
import backendproject.mystore.domain.Photos;
import backendproject.mystore.domain.PhotosRepository;
import backendproject.mystore.domain.Product;
import backendproject.mystore.domain.ProductRepository;
import backendproject.mystore.domain.Status;
import backendproject.mystore.domain.StatusRepository;

@SpringBootApplication
public class MystoreApplication {
	private static final Logger log = LoggerFactory.getLogger(MystoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MystoreApplication.class, args);
	}

@Bean
	public CommandLineRunner productDemo (ProductRepository prepository, StatusRepository srepository, PhotosRepository phrepository, AppUserRepository arepository) {
	return (args) -> {
		log.info("save products");

		AppUser user1 = new AppUser("user","First User Name","First User Lastname","FirstEmail", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6","USER");
		AppUser user2 = new AppUser("admin","Tanja","Lyubavskaya","tEmail", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C","ADMIN");
		arepository.save(user1);
		arepository.save(user2);

		Status status1 = new Status ("New");
		Status status2 = new Status("Process");
		Status status3 = new Status("Sold");
			
		srepository.save(status1);
		srepository.save(status2);
		srepository.save(status3);
		
		Product jeans= new Product ("Jeans",1,25.50,"L",null,user2,status1);
		Product ties= new Product ("Ties",5,20.00,"L",null, user2,status1);
		Product tshirt= new Product ("Ttshirt",1,25.50,"M",null,user1,status2);

		prepository.save(jeans);
		prepository.save(ties);
		prepository.save(tshirt);

		Photos photo1 = new Photos("images/jeans-428614_1280.jpg",jeans);
		Photos photo2 = new Photos("images/jeans-1161035_1280.jpg",jeans);
		Photos photo3 = new Photos("images/neckties-210347_1280.jpg",ties);
		Photos photo4 = new Photos("images/silk-tie-2846862_1280.jpg",ties);
		Photos photo5 = new Photos("images/blank-1886001_1280.png",tshirt);
			
		phrepository.save(photo1);
		phrepository.save(photo2);
		phrepository.save(photo3);
		phrepository.save(photo4);
		phrepository.save(photo5);
		
		for (Product products : prepository.findAll()) {
			log.info(products.toString());

		}
		
		
		};	
	}
}