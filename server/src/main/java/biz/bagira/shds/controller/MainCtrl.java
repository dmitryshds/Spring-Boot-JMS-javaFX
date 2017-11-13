package biz.bagira.shds.controller;


import biz.bagira.shds.dao.AdvertRepository;
import biz.bagira.shds.entity.Advert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainCtrl {

    private static Logger logger = LoggerFactory.getLogger(MainCtrl.class);

    @Autowired
    @Qualifier(value = "advertRepository")
    AdvertRepository advertRepository;

    @RequestMapping(path = "/")
    public ModelAndView mainPage(){
        ModelAndView modelAndView = new ModelAndView();
        List<Advert> adverts = advertRepository.findAll();
        modelAndView.addObject("adverts",adverts);
        modelAndView.setViewName("/index");
        return modelAndView;
    }


}
