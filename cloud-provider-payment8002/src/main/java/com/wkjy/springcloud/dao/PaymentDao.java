package com.wkjy.springcloud.dao;

import com.wkjy.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ Description   :  支付模块持久层接口
 * @ Author        :  JY
 * @ CreateDate    :  2020/3/15 16:50
 * @ UpdateUser    :  JY
 * @ UpdateDate    :  2020/3/15 16:50
 * @ UpdateRemark  :  修改内容
 * @ Version       :  1.0
 */
@Mapper
public interface PaymentDao {

    int update(Payment payment);
    List<Payment> selectAll();
    int del(Long id);
    int create(Payment payment);
}
