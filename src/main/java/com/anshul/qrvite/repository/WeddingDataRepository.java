package com.anshul.qrvite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anshul.qrvite.model.WeddingData;

@Repository
public interface WeddingDataRepository extends JpaRepository<WeddingData, Integer> {

}
