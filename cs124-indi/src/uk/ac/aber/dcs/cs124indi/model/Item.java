package uk.ac.aber.dcs.cs124indi.model;

import java.math.BigDecimal;

public interface Item {
	
	public BigDecimal getPrice();
	
	public void setPrice(BigDecimal price);
	
	public String getDecription();
	
	public void setDescription();
}
