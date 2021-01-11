package lee.andyfxq.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lee.andyfxq.model.FXQuote;
import lee.andyfxq.repository.FXQRepository;
import lee.andyfxq.repository.FXSpotRepository;
import lee.andyfxq.service.FXQService;
import lee.andyfxq.utils.FXSpotRate;

@Service("fxqService")
public class FXQServiceImpl implements FXQService {

	@Autowired
	FXSpotRate spotRate;
	
	@Autowired
	FXQRepository fxQuoteRepo;
	
	@Autowired
	FXSpotRepository fxSpotRepo;
	
	private int randseed = 500;
	
	@Override
	public List<FXQuote> getFXQuote() {
		int num = (int) (Math.random() * randseed);
		List<FXQuote> retList = spotRate.getFXQuoteList(num);
		retList.stream().forEach( fxq -> {
			fxQuoteRepo.save( fxq);
		});
		return retList;
	}

	@Override
	public List<FXQuote> getQuoteSymbol(String symbol) {
		int num = (int) (Math.random() * randseed);
		List<FXQuote> retList = spotRate.getFXQuoteList(num, symbol);
		retList.stream().forEach( fxq -> {
			fxQuoteRepo.save( fxq);
		});
		return retList;
	}

	@Override
	public List<FXQuote> getQuoteSymbolTenor(String symbol, String tenor) {
		int num = (int) (Math.random() * randseed);
		List<FXQuote> retList = spotRate.getFXQuoteList(num, symbol, tenor);
		retList.stream().forEach( fxq -> {
			fxQuoteRepo.save( fxq);
		});
		return retList;
	}

	@Override
	public List<FXQuote> getFXQuoteSorted() {
		int num = (int) (Math.random() * randseed);
		List<FXQuote> retList = spotRate.getFXQuoteList(num);
		Comparator<FXQuote> orderByKey = Comparator.comparing(FXQuote::getSymbol)
				.thenComparing(FXQuote::getTenor)
				.thenComparing(FXQuote::getPrice);
		retList = retList.stream().sorted(orderByKey).collect(Collectors.toList());
		retList.stream().forEach( fxq -> {
			fxQuoteRepo.save( fxq);
		});
		return retList;
	}

	@Override
	public List<FXQuote> getQuoteSymbolSorted(String symbol) {
		int num = (int) (Math.random() * randseed);
		List<FXQuote> retList = spotRate.getFXQuoteList(num, symbol);
		Comparator<FXQuote> orderByKey = Comparator.comparing(FXQuote::getSymbol)
				.thenComparing(FXQuote::getTenor)
				.thenComparing(FXQuote::getPrice);
		retList = retList.stream().sorted(orderByKey).collect(Collectors.toList());
		retList.stream().forEach( fxq -> {
			fxQuoteRepo.save( fxq);
		});
		return retList;
	}

	@Override
	public List<FXQuote> getQuoteSymbolTenorSorted(String symbol, String tenor) {
		int num = (int) (Math.random() * randseed);
		List<FXQuote> retList = spotRate.getFXQuoteList(num, symbol, tenor);
		Comparator<FXQuote> orderByKey = Comparator.comparing(FXQuote::getSymbol)
				.thenComparing(FXQuote::getTenor)
				.thenComparing(FXQuote::getPrice);
		retList = retList.stream().sorted(orderByKey).collect(Collectors.toList());
		retList.stream().forEach( fxq -> {
			fxQuoteRepo.save( fxq);
		});
		return retList;
	}

}
