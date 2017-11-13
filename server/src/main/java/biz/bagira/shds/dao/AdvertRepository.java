package biz.bagira.shds.dao;

import biz.bagira.shds.entity.Advert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "advertRepository")
public interface AdvertRepository  extends MongoRepository<Advert, Long> {
    Advert findFirstById(long id);
}
