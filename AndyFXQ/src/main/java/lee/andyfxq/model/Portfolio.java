package lee.andyfxq.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "portfolio")
public class Portfolio {
	
	public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	
	@Id
	private String id;
	
	@NotNull
	@NotBlank
	private String clientId;
	
	@NotNull
	@NotBlank
	private String pName;
	
	private String currency;
	private List<Stock> stocks;
	private String updated;

	public Portfolio() {
		this.updated = dtf.format(LocalDateTime.now());
	}

	public Portfolio(@NotNull @NotBlank String clientId, @NotNull @NotBlank String pName, String currency) {
		super();
		this.clientId = clientId;
		this.pName = pName;
		this.currency = currency;
		this.stocks = new ArrayList<Stock>();
		this.updated = dtf.format(LocalDateTime.now());
	}
	
	public Portfolio(@NotNull @NotBlank String clientId, @NotNull @NotBlank String pName, String currency,
			List<Stock> stocks) {
		super();
		this.clientId = clientId;
		this.pName = pName;
		this.currency = currency;
		this.stocks = stocks;
		this.updated = dtf.format(LocalDateTime.now());
	}

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final String getpName() {
		return pName;
	}

	public final void setpName(String pName) {
		this.pName = pName;
	}

	public final String getCurrency() {
		return currency;
	}

	public final void setCurrency(String currency) {
		this.currency = currency;
	}

	public final List<Stock> getStocks() {
		return stocks;
	}

	public final void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public final String getClientId() {
		return clientId;
	}

	public final String getUpdated() {
		return updated;
	}

	public final void setUpdated(String updated) {
		this.updated = updated;
	}
	
	
}
