package lee.andyfxq.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import lee.andyfxq.model.FXSpot;

public interface  FXSpotRepository  extends MongoRepository<FXSpot, String> {

}

