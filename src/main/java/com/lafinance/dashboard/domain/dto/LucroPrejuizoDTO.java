package com.lafinance.dashboard.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LucroPrejuizoDTO {
	
	private BigDecimal indiceLucroPrejuizo;
	private BigDecimal valorLucroPrejuizo;
	
	public LucroPrejuizoDTO(BigDecimal indiceLucroPrejuizo, BigDecimal valorLucroPrejuizo) {
		this.indiceLucroPrejuizo = indiceLucroPrejuizo;
		this.valorLucroPrejuizo = valorLucroPrejuizo;
	}

}
