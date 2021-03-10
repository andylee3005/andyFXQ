package lee.andyfxq.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stockSpot")
public class StockSpot {

	static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	static final DecimalFormat f = new DecimalFormat("##.00000000");
	
	@Id
	private String id;
	
	@NotNull
	@NotBlank
	private String symbol;
	
	@NotNull
	@NotBlank
	private String currency;
	
	@NotNull
	@NotBlank
	private String price;
	private BigDecimal px;
	
	private String created;
	private long createdTime;

	public StockSpot() {
		this.created = dtf.format(LocalDateTime.now());
	};
	
	public StockSpot(@NotNull @NotBlank String symbol, @NotNull @NotBlank String currency,
			@NotNull @NotBlank BigDecimal px) {
		super();
		this.symbol = symbol;
		this.currency = currency;
		this.price = f.format(px);
		this.px = px;
		this.createdTime = System.nanoTime(); //.currentTimeMillis();
		this.created = dtf.format(LocalDateTime.now());
	}
	
	public String getId() {
		return id;
	}

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

	public String getPrice() {
		return price;
	}

	public BigDecimal getPx() {
		return px;
	}

	public void setPrice(BigDecimal px) {
		this.price = f.format(px);
		this.px = px;
	}

	public String getCreated() {
		return created;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	@Override
	public String toString() {
		return "StockSpot [symbol=" + symbol + ", currency=" + currency + ", price=" + price + ", created=" + created
				+ "]";
	}

	
}
