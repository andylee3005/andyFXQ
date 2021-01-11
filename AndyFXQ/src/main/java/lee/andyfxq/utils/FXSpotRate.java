package lee.andyfxq.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lee.andyfxq.model.FXQuote;
import lee.andyfxq.model.FXSpot;

@Component
public class FXSpotRate {

	private ArrayList<String> symbolList = new ArrayList<>();
	private ArrayList<BigDecimal> priceList = new ArrayList<>();
	private ArrayList<String> tenorList = new ArrayList<>();
	private int size = 0;
	private int tenorSize = 0;

	public FXSpotRate() {
		symbolList.add("EURUSD");  priceList.add(new BigDecimal("1.1805"));
		symbolList.add("USDCAD");  priceList.add(new BigDecimal("1.3225"));
		symbolList.add("CADJPY");  priceList.add(new BigDecimal("105.8860"));
		symbolList.add("USDJPY");  priceList.add(new BigDecimal("80.0670"));
		
		tenorList.add("ON");  tenorList.add("TN");  tenorList.add("SN");  
		tenorList.add("1W");  tenorList.add("2W");  
		tenorList.add("1M");  tenorList.add("2M");  tenorList.add("3M");  tenorList.add("6M");  tenorList.add("9M"); 
		tenorList.add("1Y");
		size = symbolList.size();
		tenorSize = tenorList.size();
	}
	
	public FXSpot getSymbol(String...quoteParams ) {
		int index = quoteParams.length > 0 ? symbolList.indexOf(quoteParams[0]) : (int) (Math.random() * size);
		String tenor = (quoteParams.length > 1) ? quoteParams[1] : tenorList.get( (int) (Math.random() * tenorSize));
		FXSpot retFx = new FXSpot();
		retFx.setSymbol(symbolList.get(index));
		int pluse = (int) (Math.random() * 2);
		int range = (int) (Math.random() * 350);
		BigDecimal price = priceList.get(index);
		BigDecimal value = price.multiply( new BigDecimal(range* 0.000001));
		
		retFx.setPrice( (pluse == 0 ? price.subtract(value) : price.add(value)).setScale(6, BigDecimal.ROUND_DOWN) );
		retFx.setQuoteTime(System.currentTimeMillis());
		retFx.setTenor(tenor);
		
		priceList.remove(index);
		priceList.add(index, retFx.getPrice());
		priceList.set(index, retFx.getPrice());
		
		return retFx;
	}
	
	public List<FXQuote> getFXQuoteList(int qNum, String...params) {
		List<FXQuote> QuoteList = new ArrayList<>();
		FXSpot aFX = null;
		for (int i=0; i<qNum; i++) {
			switch (params.length) {
			case 0: aFX = getSymbol(); break;
			case 1: aFX = getSymbol(params[0]); break;
			case 2: aFX = getSymbol(params[0], params[1]); break;
			default: aFX = getSymbol();
			}
			FXQuote aFXQuote = new FXQuote(aFX.getSymbol(), aFX.getTenor(), aFX.getPxStr(), aFX);
			QuoteList.add(aFXQuote);
		}
		return QuoteList;
	}
}
