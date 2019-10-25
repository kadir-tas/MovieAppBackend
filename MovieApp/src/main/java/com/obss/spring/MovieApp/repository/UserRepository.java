package com.obss.spring.MovieApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.obss.spring.MovieApp.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

	public Optional<Users> findByUsernameOrEmail(String username, String email);
	
    public Users findByUsername(String username);

	public boolean existsByUsername(String username);

	public boolean existsByEmail(String email);

    
}
