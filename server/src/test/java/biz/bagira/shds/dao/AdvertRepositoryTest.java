package biz.bagira.shds.dao;

import biz.bagira.shds.entity.Advert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvertRepositoryTest {


    @Autowired
    @Qualifier(value = "advertRepository")
    AdvertRepository advertRepository;


    @After
    public void cleanTestDB() {
        advertRepository.deleteAll();
    }


    @Test
    public void FindByIdTest() {
        Advert advert = new Advert(1l, "adv1", "text1", new BigDecimal(10.25));
        advertRepository.insert(advert);
        Advert firstById = advertRepository.findFirstById(1);
        Assert.assertEquals(firstById, advert);
    }


}