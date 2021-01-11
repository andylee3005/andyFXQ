package lee.andyfxq.service;

import java.util.List;

import lee.andyfxq.model.FXQuote;

public interface FXQService {

	List<FXQuote> getFXQuote();
	List<FXQuote> getQuoteSymbol(String symbol);
	List<FXQuote> getQuoteSymbolTenor(String symbol, String tenor);
	
	List<FXQuote> getFXQuoteSorted();
	List<FXQuote> getQuoteSymbolSorted(String symbol);
	List<FXQuote> getQuoteSymbolTenorSorted(String symbol, String tenor);
	
	
}
