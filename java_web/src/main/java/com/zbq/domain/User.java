package com.zbq.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangboqing
 * @date 2018/4/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {


    private String userName;
    private String password;
    private String phone;

}
