package lee.andyfxq.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lee.andyfxq.model.FXQuote;
import lee.andyfxq.repository.FXQRepository;
import lee.andyfxq.service.FXQService;
import lee.andyfxq.utils.FXSpotRate;

@Service("fxqServiceMongo")
public class FXQServiceMongoImpl implements FXQService {

	@Autowired
	FXSpotRate spotRate;
	
	@Autowired
	FXQRepository fxQuoteRepo;
	
	private Comparator<FXQuote> comparatorKey() {
		Comparator<FXQuote> orderByKey = Comparator.comparing(FXQuote::getSymbol)
				.thenComparing(FXQuote::getTenor)
				.thenComparing(FXQuote::getPrice);
		return orderByKey;
	}
	
	@Override
	public List<FXQuote> getFXQuote() {
		return fxQuoteRepo.findAll().stream().sorted(comparatorKey()).collect(Collectors.toList());
	}

	@Override
	public List<FXQuote> getQuoteSymbol(String symbol) {
		return fxQuoteRepo.findBySymbol(symbol).stream().sorted(comparatorKey()).collect(Collectors.toList());
	}

	@Override
	public List<FXQuote> getQuoteSymbolTenor(String symbol, String tenor) {
		return fxQuoteRepo.findBySymbolAndTenor(symbol, tenor).stream().sorted(comparatorKey()).collect(Collectors.toList());
	}

	@Override
	public List<FXQuote> getFXQuoteSorted() {
		return getFXQuote();
	}

	@Override
	public List<FXQuote> getQuoteSymbolSorted(String symbol) {
		return getQuoteSymbol(symbol);
	}

	@Override
	public List<FXQuote> getQuoteSymbolTenorSorted(String symbol, String tenor) {
		return getQuoteSymbolTenor(symbol, tenor);
	}

}
