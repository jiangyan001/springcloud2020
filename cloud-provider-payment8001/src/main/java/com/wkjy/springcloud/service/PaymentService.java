package com.wkjy.springcloud.service;

import com.wkjy.springcloud.entity.Payment;

import java.util.List;

public interface PaymentService {
    int update(Payment payment);
    List<Payment> selectAll();
    int del(Long id);
    int create(Payment payment);
}
