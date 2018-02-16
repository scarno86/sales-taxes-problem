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

	private static final GoodItem BOOK = new GoodItem(GoodCategory.BOOK, "book", new BigDecimal("12.49"), false);
	private static final GoodItem MUSIC = new GoodItem(GoodCategory.GENERIC, "music CD", new BigDecimal("14.99"),
			false);
	private static final GoodItem CHOCOLATE = new GoodItem(GoodCategory.FOOD, "chocolate bar", new BigDecimal("0.85"),
			false);

	private static final GoodItem BOX_CHOCOLATE_IMPORTED = new GoodItem(GoodCategory.FOOD, "box of chocolates",
			new BigDecimal("10.00"), true);
	private static final GoodItem BOTTLE_PERFUME_IMPORTED_1 = new GoodItem(GoodCategory.GENERIC, "bottle of perfume",
			new BigDecimal("47.50"), true);

	private static final GoodItem BOTTLE_PERFUME_IMPORTED_2 = new GoodItem(GoodCategory.GENERIC, "bottle of perfume",
			new BigDecimal("27.99"), true);
	private static final GoodItem BOTTLE_PERFUME = new GoodItem(GoodCategory.GENERIC, "bottle of perfume",
			new BigDecimal("18.99"), false);
	private static final GoodItem PACKET_HEADACHE_PILLS = new GoodItem(GoodCategory.MEDICAL, "packet of headache pills",
			new BigDecimal("9.75"), false);
	private static final GoodItem CHOCOLATE_IMPORTED = new GoodItem(GoodCategory.FOOD, "chocolates",
			new BigDecimal("11.25"), true);

	private static final Map<Long, List<ShoppingBasketItem>> basketItemMaps = new HashMap<>();
	static {
		basketItemMaps.put(1l, Lists.newArrayList(new ShoppingBasketItem(2, BOOK), 
				new ShoppingBasketItem(1, MUSIC),
				new ShoppingBasketItem(1, CHOCOLATE)));

		basketItemMaps.put(2l, Lists.newArrayList(new ShoppingBasketItem(1, BOX_CHOCOLATE_IMPORTED),
				new ShoppingBasketItem(1, BOTTLE_PERFUME_IMPORTED_1)));

		basketItemMaps.put(3l,
				Lists.newArrayList(
						new ShoppingBasketItem(1, BOTTLE_PERFUME_IMPORTED_2),
						new ShoppingBasketItem(1, BOTTLE_PERFUME),
						new ShoppingBasketItem(1, PACKET_HEADACHE_PILLS),
						new ShoppingBasketItem(3, CHOCOLATE_IMPORTED)));
	}

	private static final Map<Long, List<ReceiptItem>> receiptItemMaps = new HashMap<>();
	static {
		receiptItemMaps.put(1l,
				Lists.newArrayList(new ReceiptItem(new ShoppingBasketItem(2, BOOK),BigDecimal.ZERO),
						new ReceiptItem(new ShoppingBasketItem(1, MUSIC), new BigDecimal("1.50")),
						new ReceiptItem(new ShoppingBasketItem(1, CHOCOLATE),BigDecimal.ZERO)));

		receiptItemMaps.put(2l, Lists.newArrayList(
				new ReceiptItem(new ShoppingBasketItem(1, BOX_CHOCOLATE_IMPORTED), new BigDecimal("0.50")),
				new ReceiptItem(new ShoppingBasketItem(1, BOTTLE_PERFUME_IMPORTED_1), new BigDecimal("7.15"))));

		receiptItemMaps.put(3l,
				Lists.newArrayList(
						new ReceiptItem(new ShoppingBasketItem(1, BOTTLE_PERFUME_IMPORTED_2), new BigDecimal("4.20")),
						new ReceiptItem(new ShoppingBasketItem(1, BOTTLE_PERFUME), new BigDecimal("1.90")),
						new ReceiptItem(new ShoppingBasketItem(1, PACKET_HEADACHE_PILLS), BigDecimal.ZERO),
						new ReceiptItem(new ShoppingBasketItem(3, CHOCOLATE_IMPORTED), new BigDecimal("1.80"))));

	}

	private static final Map<Long, Receipt> receiptMaps = new HashMap<>();
	static {
		Long idBasket = 1l;
		receiptMaps.put(idBasket, new Receipt(idBasket, "Output " + idBasket, receiptItemMaps.get(idBasket),
				new BigDecimal("1.50"), new BigDecimal("42.32")));

		idBasket = 2l;
		receiptMaps.put(idBasket, new Receipt(idBasket, "Output " + idBasket, receiptItemMaps.get(idBasket),
				new BigDecimal("7.65"), new BigDecimal("65.15")));

		idBasket = 3l;
		receiptMaps.put(idBasket, new Receipt(idBasket, "Output " + idBasket, receiptItemMaps.get(idBasket),
				new BigDecimal("7.90"), new BigDecimal("98.38")));
	}

	public static Function<Long, ShoppingBasket> createShoppingBasket = idBasket -> new ShoppingBasket(idBasket,
			"Input " + idBasket, basketItemMaps.get(idBasket));

	public static Function<Long, Receipt> createReceipt = idBasket -> receiptMaps.get(idBasket);

	public static void mockShoppingBasketService() {

		new mockit.MockUp<ShoppingBasketService>() {

			@mockit.Mock
			public ShoppingBasket loadBasketByIdBasket(Long idBasket) {

				return createShoppingBasket.apply(idBasket);
			}
		};
	}

}
