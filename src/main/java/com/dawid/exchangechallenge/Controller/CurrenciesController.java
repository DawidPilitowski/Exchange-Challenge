package com.dawid.exchangechallenge.Controller;

import com.dawid.exchangechallenge.data.Curriences;
import com.dawid.exchangechallenge.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CurrenciesController {
    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/")
    public ModelAndView getPage() {
        ModelAndView model=new ModelAndView("/index");
        model.addObject("currentCountry", exchangeService.getKeyList());
//        CurrencyConversionVO currencyConversionVO = new CurrencyConversionVO();
//        model.addAttribute("currency", currencyConversionVO);
        return model;
    }
    @PostMapping("/")
    public ModelAndView setMoney(Curriences curriences) {
        ModelAndView model=new ModelAndView("redirect:/currencyConverter");
        curriences.setName(curriences.getName());
        return model;
    }
    @GetMapping("/currencyConverter")
    public ModelAndView changePage(){
        ModelAndView model=new ModelAndView("index");
        exchangeService.getChangeCurriences();
        return model;
    }
}
