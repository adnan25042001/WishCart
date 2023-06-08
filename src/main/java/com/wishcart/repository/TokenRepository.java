package com.wishcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wishcart.model.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

	List<Token> findByUser_id(Long id);

	Optional<Token> findByToken(String token);

}
