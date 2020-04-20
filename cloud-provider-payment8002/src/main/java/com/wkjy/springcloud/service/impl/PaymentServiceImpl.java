package com.wkjy.springcloud.service.impl;

import com.wkjy.springcloud.dao.PaymentDao;
import com.wkjy.springcloud.entity.Payment;
import com.wkjy.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaymentServiceImpl  implements PaymentService {
    @Resource
    private PaymentDao dao;



    @Override
    public int update(Payment payment) {
        return dao.update(payment);
    }

    @Override
    public List<Payment> selectAll() {
        return dao.selectAll();
    }

    @Override
    public int del(Long id) {
        return dao.del(id);
    }

    @Override
    public int create(Payment payment) {
        return dao.create(payment);
    }
}
