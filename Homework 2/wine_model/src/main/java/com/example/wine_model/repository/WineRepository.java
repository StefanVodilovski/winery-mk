package com.example.wine_model.repository;

import com.example.wine_model.model.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<Wine,Long> {
}
