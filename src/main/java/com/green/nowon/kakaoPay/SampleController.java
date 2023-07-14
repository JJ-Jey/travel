package com.green.nowon.kakaoPay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class SampleController {
	@Setter(onMethod_ = @Autowired)
    private KakaoPay kakaopay;
    
    
    @GetMapping("/kakaoPay")
    public String kakaoPayGet() {
        return "kakaopay/kakaoPay";
    }
    
    @PostMapping("/kakaoPay")
    public String kakaoPay() {
    	System.out.println("이동");
        log.info("kakaoPay post............................................");
        
        return "redirect:" + kakaopay.kakaoPayReady();
 
    }
    
    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
    //public String kakaoPaySuccess(Model model) {
        //log.info("kakaoPaySuccess get............................................");
        //log.info("kakaoPaySuccess pg_token : " + pg_token);
        
        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
        
        return "kakaopay/kakaoPaySuccess";
    }
    
    @GetMapping("/kakaoPayCancel")
    public String kakaoPayCancel() {
    	return "redirect:/reservations";
    }
}
