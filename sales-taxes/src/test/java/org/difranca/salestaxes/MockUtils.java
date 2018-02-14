package org.difranca.salestaxes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.difranca.salestaxes.model.shopping.ShoppingBasket;
import org.difranca.salestaxes.model.shopping.ShoppingBasketItem;

public class MockUtils {

	private static final Map<Long, List<ShoppingBasketItem>> basketItemMaps = new HashMap<>();
    static {
       
    }
	
	private Function<Long, ShoppingBasket> createShoppingBasket =  idBasket -> new ShoppingBasket(idBasket,"Input "+idBasket, basketItemMaps.get(idBasket));
	
}
