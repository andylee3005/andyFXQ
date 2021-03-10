package lee.andyfxq.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lee.andyfxq.model.Client;
import lee.andyfxq.model.StockSpot;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/STOCK/spot")
public class StockSpotController {

	final String targetUrl = "http://192.168.0.20:4985/STOCK/spot";
	
	HttpEntity<String> setHeader(String jwt) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", jwt);
		return new HttpEntity<String>(headers);
	}
	
	@GetMapping("/{symbol}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Optional<StockSpot> requestStockSpot(@RequestHeader("Authorization") String jwt, @PathVariable String symbol) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/" + symbol;
		ResponseEntity<Optional> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, Optional.class);
		Optional spot = response.getBody();
		
		return spot;
	}
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<StockSpot> requestStockSpot(@RequestHeader("Authorization") String jwt) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/list";
		ResponseEntity<StockSpot[]> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, StockSpot[].class);
		StockSpot[] spots = response.getBody();
		
		return Arrays.asList(spots);
	}
	
	@GetMapping("id/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Optional<StockSpot> requestStockSpotById(@RequestHeader("Authorization") String jwt, @PathVariable String id) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/id/" + id;
		ResponseEntity<Optional> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, Optional.class);
		Optional spot = response.getBody();
		
		return spot;
	}
	
	@GetMapping("/symbol/{symbol}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<StockSpot> requestStockSpotBySymbol(@RequestHeader("Authorization") String jwt, @PathVariable String symbol) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/symbol/" + symbol;
		ResponseEntity<StockSpot[]> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, StockSpot[].class);
		StockSpot[] spots = response.getBody();
		
		return Arrays.asList(spots);
	}
	
	@GetMapping("/currency/{currency}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<StockSpot> requestStockSpotByCurrency(@RequestHeader("Authorization") String jwt, @PathVariable String currency) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/currency/" + currency;
		ResponseEntity<StockSpot[]> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, StockSpot[].class);
		StockSpot[] spots = response.getBody();
		
		return Arrays.asList(spots);
	}
	
	@GetMapping("/symbol/{symbol}/time/{time}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<StockSpot> requestStockSpotBySymbolAndTime(@RequestHeader("Authorization") String jwt, @PathVariable String symbol, @PathVariable long time) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/symbol/" + symbol + "/time/" + time;
		ResponseEntity<StockSpot[]> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, StockSpot[].class);
		StockSpot[] spots = response.getBody();
		
		return Arrays.asList(spots);
	}
	
	@GetMapping("/generate")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<StockSpot> generateStockSpot(@RequestHeader("Authorization") String jwt) {
		RestTemplate restTemp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", jwt);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<StockSpot> request = new HttpEntity<>(headers);
		
		String ext = "/generate";
		ResponseEntity<StockSpot[]> response = restTemp.exchange(targetUrl + ext, HttpMethod.POST, request, StockSpot[].class);
		StockSpot[] spots = response.getBody();

		return Arrays.asList(spots);
	}
	
	@GetMapping("/generate/{symbol}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<StockSpot> generateStockSpot(@RequestHeader("Authorization") String jwt, @PathVariable String symbol) {
		RestTemplate restTemp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", jwt);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<StockSpot> request = new HttpEntity<>(headers);
		
		String ext = "/generate/" + symbol;
		ResponseEntity<StockSpot[]> response = restTemp.exchange(targetUrl + ext, HttpMethod.POST, request, StockSpot[].class);
		StockSpot[] spots = response.getBody();

		return Arrays.asList(spots);
	}
	
	
}
