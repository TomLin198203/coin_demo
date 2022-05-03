package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.domain.Coin;
import com.example.demo.service.CoinInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    CoinInfoServiceImpl coinInfoServiceImpl;

    @GetMapping("/readAPI")
    public String getJson(){
        String url="https://api.coindesk.com/v1/bpi/currentprice.json";
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String json=results.getBody();
        return json;
    }

    @GetMapping("/transfer")
    public String outputJson(){
        List<Coin> coins = coinInfoServiceImpl.getAll();

        String url="https://api.coindesk.com/v1/bpi/currentprice.json";
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String json=results.getBody();
        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject bpiObject = (JSONObject) jsonObject.get("bpi");
        JSONObject usdObject = (JSONObject) bpiObject.get("USD");
        JSONObject gbpObject = (JSONObject) bpiObject.get("GBP");
        JSONObject eurObject = (JSONObject) bpiObject.get("EUR");

//        System.out.println((String)usdObject.get("code"));
//        usdObject.get("rate_float").getClass().getSimpleName();
//        double rate_float = ((BigDecimal) usdObject.get("rate_float")).doubleValue();
//        System.out.println(rate_float);

        Coin coin=new Coin();
        coin.setCode((String)usdObject.get("code"));
        coin.setName("美金");
        coin.setRate(((BigDecimal) usdObject.get("rate_float")).doubleValue());
        coins.add(coin);

        coin.setCode((String)gbpObject.get("code"));
        coin.setName("英鎊");
        coin.setRate(((BigDecimal) gbpObject.get("rate_float")).doubleValue());
        coins.add(coin);

        coin.setCode((String)eurObject.get("code"));
        coin.setName("歐元");
        coin.setRate(((BigDecimal) eurObject.get("rate_float")).doubleValue());
        coins.add(coin);

        //Coin coin = coins.get(0);
        return JSONObject.toJSONStringWithDateFormat(coins,"yyyy/MM/dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
        //return JSONObject.toJSONString(coins);
    }
}
