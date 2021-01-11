package lee.andyfxq.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import lee.andyfxq.model.FXQuote;

public interface FXQRepository extends MongoRepository<FXQuote, String> {
	
	List<FXQuote> findBySymbol(String symbol);
	List<FXQuote> findBySymbolAndTenor(String symbol, String tenor);
	
}
