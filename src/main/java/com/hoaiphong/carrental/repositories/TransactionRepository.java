package com.hoaiphong.carrental.repositories;

import java.util.*;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hoaiphong.carrental.entities.Transaction;
import com.hoaiphong.carrental.entities.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID>, JpaSpecificationExecutor<Transaction> {

    Page<Transaction> findByUser(User user, Pageable pageable);

    Page<Transaction> findByUserAndDateTimeBetween(User user, LocalDate  startDate, LocalDate  endDate, Pageable pageable);
}