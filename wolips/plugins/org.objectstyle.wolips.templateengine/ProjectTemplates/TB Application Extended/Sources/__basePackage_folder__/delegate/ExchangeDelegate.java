package ${basePackage}.delegate;

import java.math.BigDecimal;

import org.treasureboat.foundation.TBFConstants;
import org.treasureboat.foundation.enums.ETBFMoney;
import org.treasureboat.foundation.iface.ITBFExchangeRate;

public class ExchangeDelegate implements ITBFExchangeRate {

	//********************************************************************
	//  Methods : メソッド
	//********************************************************************

	@Override
	public BigDecimal exchangeRate(ETBFMoney from, ETBFMoney to) {

		if (from == null || to == null) {
			return TBFConstants.OneBigDecimal;
		}

		if (from.equals(to)) {
			return TBFConstants.OneBigDecimal;
		}

		// TODO : do your conversion stuff

		return TBFConstants.OneBigDecimal;
	}
}
