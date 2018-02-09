package org.difranca.salestaxes.model.goods;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodItem {


	private GoodCategory category;
	
	private String description;
	
	private BigDecimal unityPrice; 
	
	
}
