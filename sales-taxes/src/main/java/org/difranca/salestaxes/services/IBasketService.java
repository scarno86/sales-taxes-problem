package org.difranca.salestaxes.services;

import java.util.List;

import org.difranca.salestaxes.model.shopping.ShoppingBasket;

public interface IBasketService {

	List<ShoppingBasket> loadAllShoppingBasket();
	
	ShoppingBasket loadBasketByIdBasket(Long idBasket);
	
}
