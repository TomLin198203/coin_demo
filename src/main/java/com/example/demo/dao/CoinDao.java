package com.example.demo.dao;

import com.example.demo.domain.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CoinDao extends JpaRepository<Coin,Long>, JpaSpecificationExecutor<Coin> {
}
