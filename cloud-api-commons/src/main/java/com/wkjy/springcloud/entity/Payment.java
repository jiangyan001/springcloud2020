package com.wkjy.springcloud.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ Description   :  payment实体类
 * @ Author        :  JY
 * @ CreateDate    :  2020/3/14 13:15
 * @ UpdateUser    :  JY
 * @ UpdateDate    :  2020/3/14 13:15
 * @ UpdateRemark  :  修改内容
 * @ Version       :  1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {
    private long id;
    private String serial;
}
