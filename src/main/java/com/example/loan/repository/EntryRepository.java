package com.example.loan.repository;

import com.example.loan.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping
public interface EntryRepository extends JpaRepository<Entry, Long> {

    Optional<Entry> findByApplicationId(Long applicationId);
}
