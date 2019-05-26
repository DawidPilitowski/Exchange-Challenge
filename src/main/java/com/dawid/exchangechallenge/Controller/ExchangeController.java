package com.dawid.exchangechallenge.Controller;

import com.dawid.exchangechallenge.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/exchange")
    public ModelAndView changeMoney() {
        ModelAndView model= new ModelAndView("/exchange");
        model.addObject("rate", exchangeService.getChangeCurriences());
        return model;
    }
}
