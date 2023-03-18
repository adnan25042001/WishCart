package com.wishcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wishcart.model.Admin;
import com.wishcart.model.Customer;
import com.wishcart.model.SoldProduct;

@Repository
public interface SoldProductDao extends JpaRepository<SoldProduct, Integer> {

	public List<SoldProduct> findAllByCustomerOrderByPurchaseDateDesc(Customer customer);

	public List<SoldProduct> findAllByAdminOrderByPurchaseDateDesc(Admin admin);

}
