package com.example.demo.service;

import com.example.demo.dao.CoinDao;
import com.example.demo.domain.Coin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoinInfoServiceImpl implements CoinInfoService {

    @Autowired
    private CoinDao coinDao;

    @Override
    public List<Coin> getAll() {
        List<Coin> list = coinDao.findAll();
        return list;
    }

    @Override
    public void add(Coin coin) {
        coinDao.save(coin);
    }

    @Override
    public void delete(Long id) {
        coinDao.deleteById(id);
    }

    @Override
    public void update(Coin coin) {
        coinDao.save(coin);
    }

    @Override
    public Coin findOne(Long id) {
        return coinDao.getById(id);
    }
}
