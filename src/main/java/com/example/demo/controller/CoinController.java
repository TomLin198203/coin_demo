package com.example.demo.controller;

import com.example.demo.domain.Coin;
import com.example.demo.service.CoinInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class CoinController {

    @Autowired
    CoinInfoServiceImpl coinInfoService;

    @GetMapping("/list")
    public String getAll(Model model){
        List<Coin> coinList = coinInfoService.getAll();
//        if(coinList.isEmpty())
//        {
//            System.out.println("coinList is null");
//        }
//        System.out.println(coinList.toString());
        model.addAttribute("coinList",coinList);
        return "list";
    }

    @GetMapping("/add")
    public String add(){

        return "add";
    }

    @PostMapping("/coin")
    public String addOne(Coin coin){
        coin.setUpdate_time(new Date());
        coinInfoService.add(coin);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCoin(@PathVariable("id") Long id){
        System.out.println(id);
        coinInfoService.delete(id);
        return "redirect:/list";
    }

    @GetMapping("/updatePage/{id}")
    public String updatePage(@PathVariable("id") Long id, Model model){
        //System.out.println("修改"+id);
        Coin coin = coinInfoService.findOne(id);
        model.addAttribute("coin",coin);
        return "/update.html";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,Coin coin){
        coin.setId(id);
        coin.setUpdate_time(new Date());
        coinInfoService.update(coin);
        return "redirect:/list";
    }


}
