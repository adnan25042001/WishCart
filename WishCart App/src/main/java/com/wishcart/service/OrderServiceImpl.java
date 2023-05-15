package com.wishcart.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishcart.dto.CardDto;
import com.wishcart.exception.CardException;
import com.wishcart.exception.CartException;
import com.wishcart.exception.CustomerException;
import com.wishcart.exception.OrderException;
import com.wishcart.exception.ProductException;
import com.wishcart.model.Card;
import com.wishcart.model.Cart;
import com.wishcart.model.CurrentUserSession;
import com.wishcart.model.Customer;
import com.wishcart.model.Order;
import com.wishcart.model.Product;
import com.wishcart.model.SoldProduct;
import com.wishcart.model.SuccessMessage;
import com.wishcart.repository.CardDao;
import com.wishcart.repository.CartDao;
import com.wishcart.repository.CurrentUserSessionDao;
import com.wishcart.repository.CustomerDao;
import com.wishcart.repository.ProductDao;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CurrentUserSessionDao cusdao;

	@Autowired
	private CustomerDao customerdao;

	@Autowired
	private CartDao cartdao;

	@Autowired
	private CardDao carddao;

	@Autowired
	private ProductDao productdao;

	@Autowired
	private SoldProductService soldproductservice;

	@Override
	public SuccessMessage placeOrder(CardDto carddto, String authKey) throws OrderException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		Customer customer = customerdao.findByEmail(cus.getEmail())
				.orElseThrow(() -> new CustomerException("Invalid authKey"));

		List<Cart> carts = cartdao.findAllByCustomerOrderByCreatedDateDesc(customer);

		if (carts.size() == 0)
			throw new CartException("Cart is empty");

		Card card = carddao.findByCardHolderAndCardNumber(carddto.getCardHolder(), carddto.getCardNumber())
				.orElseThrow(() -> new CardException("Invalid card details"));

		if (customer.getId() != card.getCustomer().getId())
			throw new CardException("Invalid card details");

		Double totalPrice = (double) 0;

		for (Cart c : carts) {

			totalPrice += c.getQuantity() * c.getProduct().getPrice();

			SoldProduct soldProduct = new SoldProduct();

			soldProduct.setCustomer(c.getCustomer());
			soldProduct.setProduct(c.getProduct());
			soldProduct.setPurchaseDate(new Date());
			soldProduct.setQuantity(c.getQuantity());
			soldProduct.setAdmin(c.getProduct().getAdmin());

			soldproductservice.addToSoldProduct(soldProduct, authKey);

		}

		Order order = new Order();

		order.setOrderdate(new Date());
		order.setStatus("Delivered");
		order.setTotalPrice(totalPrice);

		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(1);
		List<Order> list = new ArrayList<>();
		list.add(order);
		successMessage.setResult(list);

		return successMessage;

	}

	@Override
	public SuccessMessage placeOrderByProductId(CardDto carddto, Integer id, String authKey) throws OrderException {

		CurrentUserSession cus = cusdao.findByAuthKey(authKey)
				.orElseThrow(() -> new CustomerException("User not logged in"));

		Customer customer = customerdao.findByEmail(cus.getEmail())
				.orElseThrow(() -> new CustomerException("Invalid authKey"));

		Product product = productdao.findById(id)
				.orElseThrow(() -> new ProductException("Product not found with id : " + id));

		Card card = carddao.findByCardHolderAndCardNumber(carddto.getCardHolder(), carddto.getCardNumber())
				.orElseThrow(() -> new CardException("Invalid card details"));

		if (customer.getId() != card.getCustomer().getId())
			throw new CardException("Invalid card details");

		SoldProduct soldProduct = new SoldProduct();

		soldProduct.setCustomer(customer);
		soldProduct.setProduct(product);
		soldProduct.setPurchaseDate(new Date());
		soldProduct.setQuantity(1);
		soldProduct.setAdmin(product.getAdmin());

		soldproductservice.addToSoldProduct(soldProduct, authKey);

		Order order = new Order();

		order.setOrderdate(new Date());
		order.setStatus("Delivered");
		order.setTotalPrice(product.getPrice());

		SuccessMessage successMessage = new SuccessMessage();
		successMessage.setTotalResult(1);
		List<Order> list = new ArrayList<>();
		list.add(order);
		successMessage.setResult(list);

		return successMessage;

	}

}
