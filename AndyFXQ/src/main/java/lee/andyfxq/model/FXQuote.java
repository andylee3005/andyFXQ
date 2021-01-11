package lee.andyfxq.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@XmlRootElement(name = "FXQuote")
@XmlAccessorType(XmlAccessType.FIELD)
@Document
public class FXQuote implements Comparable<FXQuote> {
	static final DecimalFormat f = new DecimalFormat("##.00000000");
	
	@Id
	private String fxqId;
	
	@NotNull
	@NotBlank
	private String symbol;
	@NotNull
	@NotBlank
	private String tenor;
	private String pxStr;
	private FXSpot fxSpot;
	private String price;

	public FXQuote(String symbol,String tenor, String price, FXSpot fxSpot) {
		this.symbol = symbol;
		this.tenor = tenor;
		this.pxStr = price;
		this.fxSpot = fxSpot;
		this.price = price;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPrice() {
		return pxStr;
	}

	public void setPrice(String pxStr) {
		this.pxStr = pxStr;
	}

	public String getTenor() {
		return tenor;
	}

	public void setTenor(String tenor) {
		this.tenor = tenor;
	}

	public FXSpot getFxSpot() {
		return fxSpot;
	}

	public void setFxSpot(FXSpot fxSpot) {
		this.fxSpot = fxSpot;
	}
	
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("symbol="+symbol+"|");
		strBuf.append("tenor="+tenor+"|");
		strBuf.append("price="+pxStr+"||");
		
		return strBuf.toString();
	}

	@Override
	public int compareTo(FXQuote o) {
		return this.symbol.compareTo(((FXQuote)o).getSymbol());
	}
	
	
}
