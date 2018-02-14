package org.difranca.salestaxes.model.goods;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GoodItem {

	private GoodCategory category;
	
	private String description;
	
	private BigDecimal unityPrice; 
	
	private Boolean imported;
	
	@Override
	public String toString() {
		return new StringBuilder(this.imported ? " imported ": " ").append(description).append(" at ").append(this.unityPrice).toString();
	}
	
}
