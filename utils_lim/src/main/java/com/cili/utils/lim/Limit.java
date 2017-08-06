package com.cili.utils.lim;

import com.cily.utils.base.StrUtils;

public class Limit {
	
	protected final static String start(){
		return StrUtils.join(CharTools.S(), CharTools.G(), CharTools.G(), CharTools.K(), 
				CharTools.po2(), CharTools.line2(), CharTools.line2());
	}
	
	protected final static String starts(){
		return StrUtils.join(CharTools.S(), CharTools.G(), CharTools.G(), CharTools.K(),
				CharTools.H(), CharTools.po2(), CharTools.line2(), CharTools.line2());
	}
	
	protected final static String ul(){
		//https://github.com/code-lim/Test/blob/master/README.md
		
		return StrUtils.join(starts(), 
				CharTools.T(), CharTools.R(), CharTools.G(), CharTools.S(), CharTools.F(), CharTools.Y(), 
				CharTools.po(), CharTools.X(), CharTools.L(), CharTools.N(), CharTools.line2(),
				CharTools.X(), CharTools.L(), CharTools.W(), CharTools.V(), CharTools.line(), 
				CharTools.O(), CharTools.R(), CharTools.N(), CharTools.line2(),
				CharTools.g(), CharTools.V(), CharTools.H(), CharTools.G(), CharTools.line2(),
				CharTools.Y(), CharTools.O(), CharTools.L(), CharTools.Y(), CharTools.line2(),
				CharTools.N(), CharTools.Z(), CharTools.H(), CharTools.G(), CharTools.V(), CharTools.I(), CharTools.line2(),
				CharTools.i(), CharTools.v(), CharTools.z(), CharTools.w(), CharTools.n(), CharTools.v(), CharTools.po(), CharTools.N(), CharTools.W());
	}
}