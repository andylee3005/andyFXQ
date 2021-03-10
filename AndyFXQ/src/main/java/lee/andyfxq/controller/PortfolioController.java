package lee.andyfxq.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lee.andyfxq.model.Portfolio;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/PORT")
public class PortfolioController {
	
	final String targetUrl = "http://192.168.0.20:4986/PORT";
	
	HttpEntity<String> setHeader(String jwt) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", jwt);
		return new HttpEntity<String>(headers);
	}
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Portfolio> requestPortfolios(@RequestHeader("Authorization") String jwt) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/list";
		ResponseEntity<Portfolio[]> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, Portfolio[].class);
		
		return Arrays.asList(response.getBody());
	}
	
	@GetMapping("/id/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Optional<Portfolio> requestById(@RequestHeader("Authorization") String jwt, @PathVariable String id) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/id/" + id;
		ResponseEntity<Optional> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, Optional.class);
		
		return response.getBody();
	}
	
	@GetMapping("/cid/{cid}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Portfolio> requestByClientId(@RequestHeader("Authorization") String jwt, @PathVariable String cid) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/cid/" + cid;
		ResponseEntity<Portfolio[]> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, Portfolio[].class);
		
		return Arrays.asList(response.getBody());
	}
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Portfolio> createPortfolio(@RequestHeader("Authorization") String jwt, @RequestBody Portfolio portf) {
		RestTemplate restTemp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", jwt);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Portfolio> request = new HttpEntity<>(portf, headers);
		
		String ext = "/create";
		ResponseEntity<Portfolio> response = restTemp.exchange(targetUrl + ext, HttpMethod.POST, request, Portfolio.class, portf);
		
		return response;
	}
	
	@PutMapping("/edit/{id}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Portfolio> editPortfolio(@RequestHeader("Authorization") String jwt, @PathVariable String id, @RequestBody Portfolio portf) {
		RestTemplate restTemp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", jwt);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Portfolio> request = new HttpEntity<>(portf, headers);
		String ext = "/edit/" + id;
		ResponseEntity<Portfolio> response = restTemp.exchange(targetUrl + ext, HttpMethod.PUT, request, Portfolio.class, portf);
		
		return response;
	}
	
	@DeleteMapping("/edit/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deletePortfolio(@RequestHeader("Authorization") String jwt, @PathVariable String id) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/edit/" + id;
		ResponseEntity<HttpStatus> response = restTemp.exchange(targetUrl + ext, HttpMethod.DELETE, request, HttpStatus.class);
		
		return response;
	}
}
