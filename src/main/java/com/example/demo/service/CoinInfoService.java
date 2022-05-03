package com.example.demo.service;

import com.example.demo.domain.Coin;

import java.util.List;

public interface CoinInfoService {

    List<Coin> getAll();

    void add(Coin coin);

    void delete(Long id);

    void update(Coin coin);

    Coin findOne(Long id);

}
