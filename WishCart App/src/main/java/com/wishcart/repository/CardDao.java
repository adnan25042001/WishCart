package com.wishcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wishcart.model.Card;
import com.wishcart.model.Customer;

public interface CardDao extends JpaRepository<Card, Integer> {

	public Optional<Card> findByCardHolderAndCardNumber(String cardHolder, String cardNumber);

	public List<Card> findByCustomer(Customer customer);

}
