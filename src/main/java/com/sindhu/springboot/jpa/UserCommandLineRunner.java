package com.sindhu.springboot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);
	@Autowired
	private UserRepository userRepos;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		userRepos.save(new User("Sindhu", "Admin"));
		userRepos.save(new User("Sasi","User"));
		userRepos.save(new User("Yamini", "Admin"));
		userRepos.save(new User("Sowmya","User"));
		userRepos.save(new User("Vanaja","Admin"));
	
		for(User user: userRepos.findAll()) {
			log.info(user.toString());
		}
		
		log.info("Admin users are........");
		log.info("-----------------------------");
		for(User user : userRepos.findByRole("Admin")) {
			log.info(user.toString());
		}
	}

}
