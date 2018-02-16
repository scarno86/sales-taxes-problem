package org.difranca.salestaxes;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.assertj.core.util.Lists;
import org.difranca.salestaxes.model.goods.GoodCategory;
import org.difranca.salestaxes.model.goods.GoodItem;
import org.difranca.salestaxes.model.shopping.ShoppingBasket;
import org.difranca.salestaxes.model.shopping.ShoppingBasketItem;
import org.difranca.salestaxes.model.shopping.receipt.Receipt;
import org.difranca.salestaxes.model.shopping.receipt.ReceiptItem;
import org.difranca.salestaxes.services.shopping.impl.ShoppingBasketService;


public class MockUtils {

	private static final GoodItem BOOK = new GoodItem(GoodCategory.BOOK,"book", new BigDecimal("12.49"), false);
	private static final GoodItem MUSIC = new GoodItem(GoodCategory.GENERIC,"music CD", new BigDecimal("14.99"), false);
	private static final GoodItem CHOCOLATE = new GoodItem(GoodCategory.FOOD,"chocolate bar", new BigDecimal("0.85"), false);
	
	private static final GoodItem BOX_CHOCOLATE = new GoodItem(GoodCategory.FOOD,"box of chocolates", new BigDecimal("10.00"), TRUE);
	private static final GoodItem BOTTLE_PERFUME_IMPORTED_1 = new GoodItem(GoodCategory.GENERIC,"bottle of perfume", new BigDecimal("47.50"), TRUE);
	
	private static final GoodItem BOTTLE_PERFUME_IMPORTED_2 = new GoodItem(GoodCategory.GENERIC,"bottle of perfume", new BigDecimal("27.99"), TRUE);
	private static final GoodItem BOTTLE_PERFUME = new GoodItem(GoodCategory.GENERIC,"bottle of perfume", new BigDecimal("18.99"), FALSE);
	
	
	//1 packet of headache pills at 9.75
	//3 box of imported chocolates at 11.25
	
	private static final Map<Long, List<ShoppingBasketItem>> basketItemMaps = new HashMap<>();
    static {
    		basketItemMaps.put(1l, Lists.newArrayList(new ShoppingBasketItem(2,BOOK), new ShoppingBasketItem(1,MUSIC), new ShoppingBasketItem(1,CHOCOLATE)));
//    		basketItemMaps.put(2l, Lists.newArrayList(elements));
//    		basketItemMaps.put(3l, Lists.newArrayList(elements));
    }
    
    private static final Map<Long, List<ReceiptItem>> receiptItemMaps = new HashMap<>();
    static {
    		receiptItemMaps.put(1l, Lists.newArrayList(new ReceiptItem(new ShoppingBasketItem(2,BOOK), new BigDecimal("0.00")), 
    				new ReceiptItem(new ShoppingBasketItem(1,MUSIC), new BigDecimal("1.50")),
    				new ReceiptItem(new ShoppingBasketItem(1,CHOCOLATE), new BigDecimal("0.00"))
    				));

    }
	
	public static Function<Long, ShoppingBasket> createShoppingBasket =  idBasket -> new ShoppingBasket(idBasket,"Input "+idBasket, basketItemMaps.get(idBasket));
	
	public static Function<Long, Receipt> createReceipt =  idBasket -> new Receipt(idBasket,"Output "+idBasket, receiptItemMaps.get(idBasket), new BigDecimal("1.50"),new BigDecimal("42.32"));
	
	public static void mockShoppingBasketService() {
		
		new mockit.MockUp<ShoppingBasketService>() {

			@mockit.Mock
			public ShoppingBasket loadBasketByIdBasket(Long idBasket) {
				
				return createShoppingBasket.apply(idBasket);
			}	
		};
	}
	
	
}
