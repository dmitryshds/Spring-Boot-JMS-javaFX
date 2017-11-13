package biz.bagira.shds.listener;


import biz.bagira.shds.dao.AdvertRepository;
import biz.bagira.shds.entity.Advert;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JmsConsumer {
    private static Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

    @Autowired
    @Qualifier(value = "advertRepository")
    AdvertRepository advertRepository;

    @JmsListener(destination = "${activemq.queue-name}")
    public void consume(String msg) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!" + msg);
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + msg);
        ObjectMapper mapper = new ObjectMapper();
        Advert advert = null;
        try {
             advert = mapper.readValue(msg, Advert.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(advert!=null){
            advertRepository.save(advert);
            System.out.println("SAVE "+advert);
        }

    }

}
