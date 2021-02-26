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

import lee.andyfxq.model.Client;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/CLIENT")
public class ClientController {
	
//	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	final String targetUrl = "http://localhost:4984/CLIENT";
	
	HttpEntity<String> setHeader(String jwt) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", jwt);
		return new HttpEntity<String>(headers);
	}
	
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Client> requestClients(@RequestHeader("Authorization") String jwt) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/list";
		ResponseEntity<Client[]> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, Client[].class);
		Client[] clients = response.getBody();
		
		return Arrays.asList(clients);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/name/{fn}/{ln}")
	public List<Client> requestByName(@RequestHeader("Authorization") String jwt, @PathVariable String fn, @PathVariable String ln) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/name/" + fn + "/" + ln;
		ResponseEntity<Client[]> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, Client[].class);
		Client[] clients = response.getBody();
		
		return Arrays.asList(clients);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/email/{email}")
	public List<Client> requestByEmail(@RequestHeader("Authorization") String jwt, @PathVariable String email) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/email/" + email;
		ResponseEntity<Client[]> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, Client[].class);
		Client[] clients = response.getBody();
		
		return Arrays.asList(clients);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/address/{address}")
	public List<Client> requestByAddress(@RequestHeader("Authorization") String jwt, @PathVariable String address) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/address/" + address;
		ResponseEntity<Client[]> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, Client[].class);
		Client[] clients = response.getBody();
		
		return Arrays.asList(clients);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/phone/{phone}")
	public List<Client> requestByPhone(@RequestHeader("Authorization") String jwt, @PathVariable String phone) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/phone/" + phone;
		ResponseEntity<Client[]> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, Client[].class);
		Client[] clients = response.getBody();
		
		return Arrays.asList(clients);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/id/{id}")
	public Optional<Client> requestById(@RequestHeader("Authorization") String jwt,@PathVariable String id) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/id/" + id;
		ResponseEntity<Optional> response = restTemp.exchange(targetUrl + ext, HttpMethod.GET, request, Optional.class);
		Optional clients = response.getBody();
		
		return clients;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<Client> createClient(@RequestHeader("Authorization") String jwt, @RequestBody Client client) {
		RestTemplate restTemp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", jwt);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Client> request = new HttpEntity<>(client, headers);
		
		String ext = "/create";
		ResponseEntity<Client> response = restTemp.exchange(targetUrl + ext, HttpMethod.POST, request, Client.class, client);
		
		return response;
	}
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@PutMapping("/edit/{id}")
	public ResponseEntity<Client> editClient(@RequestHeader("Authorization") String jwt, @PathVariable("id") String id, @RequestBody Client client) {
		RestTemplate restTemp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", jwt);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Client> request = new HttpEntity<>(client, headers);
		String ext = "/edit/" + id;
		ResponseEntity<Client> response = restTemp.exchange(targetUrl + ext, HttpMethod.PUT, request, Client.class, client);
		
		return response;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/edit/{id}")
	public ResponseEntity<HttpStatus> deleteClient(@RequestHeader("Authorization") String jwt, @PathVariable("id") String id) {
		RestTemplate restTemp = new RestTemplate();
		HttpEntity<String> request = setHeader(jwt);
		String ext = "/edit/" + id;
		ResponseEntity<HttpStatus> response = restTemp.exchange(targetUrl + ext, HttpMethod.DELETE, request, HttpStatus.class);
		
		return response;
	}
}
