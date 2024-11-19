package com.hoaiphong.carrental.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import com.hoaiphong.carrental.dtos.transaction.TransactionDTO;
import com.hoaiphong.carrental.dtos.transaction.TransactionUpdateWalletDTO;
import com.hoaiphong.carrental.entities.User;

public interface TransactionService {

    List<TransactionDTO> findAll();

    TransactionDTO findById(UUID id);

    TransactionDTO create(TransactionDTO transactionDTO);

    TransactionDTO create(TransactionUpdateWalletDTO transactionUpdateWalletDTO);

    Page<TransactionDTO> findByUser(User user, Pageable pageable);

    Page<TransactionDTO> findByUserAndDate(User user, LocalDate  startDate, LocalDate  endDate, Pageable pageable);

}