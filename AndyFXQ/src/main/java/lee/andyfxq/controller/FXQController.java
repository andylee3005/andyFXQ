package lee.andyfxq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lee.andyfxq.model.FXQuote;
import lee.andyfxq.service.FXQService;

@RestController
@RequestMapping("/FXQ")
public class FXQController {
//	private final static Logger logger = LoggerFactory.getLogger(FXQController.class );
	
	@Autowired
	@Qualifier("fxqService")
	FXQService fxqService;
	
	@Autowired
	@Qualifier("fxqServiceMongo")
	FXQService fxqServiceMongo;
	
	//functions for mongodb
	@CrossOrigin
	@GetMapping("/list")
	public List<FXQuote> requestFXQuote() {
		return fxqServiceMongo.getFXQuote();
	}
	
	@CrossOrigin
	@GetMapping("/symbol/{symbol}")
	public List<FXQuote> requestFXQuote(@PathVariable String symbol) {
		return fxqServiceMongo.getQuoteSymbol(symbol);
	}
	
	@CrossOrigin
	@GetMapping("/symbol/{symbol}/tenor/{tenor}")
	public List<FXQuote> requestFXQuote(@PathVariable String symbol, @PathVariable String tenor) {
		return fxqServiceMongo.getQuoteSymbolTenor(symbol, tenor);
	}

	//functions to create data
	@CrossOrigin
	@GetMapping("/slist")
	public List<FXQuote> requestFXQuoteSorted() {
		return fxqService.getFXQuote();
	}
	
	@CrossOrigin
	@GetMapping("/ssymbol/{symbol}")
	public List<FXQuote> requestFXQuoteSorted(@PathVariable String symbol) {
		return fxqService.getQuoteSymbolSorted(symbol);
	}
	
	@CrossOrigin
	@GetMapping("/ssymbol/{symbol}/tenor/{tenor}")
	public List<FXQuote> requestFXQuoteSorted(@PathVariable String symbol, @PathVariable String tenor) {
		return fxqService.getQuoteSymbolTenorSorted(symbol, tenor);
	}
	
}
