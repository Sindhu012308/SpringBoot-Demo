package com.sindhu.springboot.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//Annotation is similar to @Repository but it also customize the mapping.
//CollectionResourceRel is used when generating links to the collection resource.
@RepositoryRestResource(path="users", collectionResourceRel="users")
public interface UserRestRepository extends PagingAndSortingRepository<User, Long>{

	// If you are having large data, then PagingAndSortingRepository will divide that data into pages
	//and sort them. this is similar to crud repository but in addition we can perform restful methods.
	List<User> findByRole(@Param("role") String role);
}

/*
 for example go to postman :
 GET method,
 http://localhost:8080/users?page=0&size=2  will display first two users in one page 
 http://localhost:8080/users?page=1&size=2  will display next two users in second page 
 http://localhost:8080/users?sort=name,desc will display the names of users in descending order
 http://localhost:8080/users/search/findByRole?role=Admin will find the users with role as Admin 
 */ 
