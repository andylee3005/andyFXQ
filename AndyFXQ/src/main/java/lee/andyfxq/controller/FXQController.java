package lee.andyfxq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lee.andyfxq.model.FXQuote;
import lee.andyfxq.service.FXQService;

@CrossOrigin(origins = "*", maxAge = 3600)
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
	
	@GetMapping("/list")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<FXQuote> requestFXQuote() {
		return fxqServiceMongo.getFXQuote();
	}
	
	@GetMapping("/symbol/{symbol}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<FXQuote> requestFXQuote(@PathVariable String symbol) {
		return fxqServiceMongo.getQuoteSymbol(symbol);
	}
	
	@GetMapping("/symbol/{symbol}/tenor/{tenor}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<FXQuote> requestFXQuote(@PathVariable String symbol, @PathVariable String tenor) {
		return fxqServiceMongo.getQuoteSymbolTenor(symbol, tenor);
	}

	//functions to create data
	@GetMapping("/slist")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<FXQuote> requestFXQuoteSorted() {
		return fxqService.getFXQuote();
	}
	
	@GetMapping("/ssymbol/{symbol}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<FXQuote> requestFXQuoteSorted(@PathVariable String symbol) {
		return fxqService.getQuoteSymbolSorted(symbol);
	}
	
	@GetMapping("/ssymbol/{symbol}/tenor/{tenor}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<FXQuote> requestFXQuoteSorted(@PathVariable String symbol, @PathVariable String tenor) {
		return fxqService.getQuoteSymbolTenorSorted(symbol, tenor);
	}
	
}
