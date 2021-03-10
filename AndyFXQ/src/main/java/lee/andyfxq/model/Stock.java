package lee.andyfxq.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

//@Document(collection = "stocks")
public class Stock {
	
	static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//	static final DecimalFormat f = new DecimalFormat("##.00000000");

	@Id
	private String id;
	
	@NotNull
	@NotBlank
	private String spotId;
	
	@NotNull
	@NotBlank
	private String symbol;
	
	@NotNull
	@NotBlank
	private String currency;
	
	@NotNull
	@NotBlank
	private String volume;
	
	@NotNull
	@NotBlank
	private String price;
	
	private String timePurchased;
	private long timeDate;

	public Stock(@NotNull @NotBlank String spotId, @NotNull @NotBlank String symbol, @NotNull @NotBlank String currency, @NotNull @NotBlank String volume,
			@NotNull @NotBlank String price) {
		super();
		this.spotId = spotId;
		this.symbol = symbol;
		this.currency = currency;
		this.volume = volume;
		this.price = price;
		this.timePurchased = dtf.format(LocalDateTime.now());
		this.timeDate = System.nanoTime();

	}

	public String getSpotId() {
		return spotId;
	}

//	public void setSpotId(String spotId) {
//		this.spotId = spotId;
//	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPurchased() {
		return timePurchased;
	}

	public long getTimeDate() {
		return timeDate;
	}


	
}
