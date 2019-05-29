package com.dawid.exchangechallenge.Controller;

import com.dawid.exchangechallenge.data.CurrenciesDTO;
import com.dawid.exchangechallenge.data.CurrencyConversionVO;
import com.dawid.exchangechallenge.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CurrenciesController {
    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/add";
    }

    @PostMapping("/add")
    public String setMoney(CurrenciesDTO dto, Model model, CurrencyConversionVO currencyConversionVO) {
        exchangeService.add(dto);
        List list = exchangeService.listAll(currencyConversionVO);
        return "redirect:/add";
    }

    @GetMapping("/currencyConverter")
    public ModelAndView changePage() {
        ModelAndView model = new ModelAndView("index");
        return model;
    }

    @GetMapping("/add")
    public String getPage(Model model, CurrenciesDTO dto, CurrencyConversionVO currencyConversionVO) {
        model.addAttribute("currentCountry", exchangeService.getKeyList());
        model.addAttribute("transferToBack", new CurrenciesDTO());
        List<CurrencyConversionVO> list = exchangeService.listAll(currencyConversionVO);

        if (list.size() == 0) {
            model.addAttribute("currencies", exchangeService.getChangeCurrencies(dto));
        } else {
            if (list.size() == 0) {
                model.addAttribute("currencies", exchangeService.getChangeCurrencies(dto));

            } else {
                model.addAttribute("currencies", list.get(list.size() - 1).getConvertedAmount());
                model.addAttribute("from", list.get(list.size() - 1).getSourceCurrency());
                model.addAttribute("to", list.get(list.size() - 1).getTargetCurrency());
            }
        }
        return "/index";
    }
}
