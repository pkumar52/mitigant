package com.itemservice.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itemservice.java.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
