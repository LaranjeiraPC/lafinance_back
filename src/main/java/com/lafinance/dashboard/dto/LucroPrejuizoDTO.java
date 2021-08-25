package com.lafinance.dashboard.dto;

import java.math.BigDecimal;

public class LucroPrejuizoDTO {
	
	private BigDecimal indiceLucroPrejuizo;
	private BigDecimal valorLucroPrejuizo;
	
	public LucroPrejuizoDTO(BigDecimal indiceLucroPrejuizo, BigDecimal valorLucroPrejuizo) {
		this.indiceLucroPrejuizo = indiceLucroPrejuizo;
		this.valorLucroPrejuizo = valorLucroPrejuizo;
	}
	
	public BigDecimal getIndiceLucroPrejuizo() {
		return indiceLucroPrejuizo;
	}
	public void setIndiceLucroPrejuizo(BigDecimal indiceLucroPrejuizo) {
		this.indiceLucroPrejuizo = indiceLucroPrejuizo;
	}
	public BigDecimal getValorLucroPrejuizo() {
		return valorLucroPrejuizo;
	}
	public void setValorLucroPrejuizo(BigDecimal valorLucroPrejuizo) {
		this.valorLucroPrejuizo = valorLucroPrejuizo;
	}

}
