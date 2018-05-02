package com.zbq.dao.mapper;

import cn.bqmart.mybatis.BaseMapper;
import com.zbq.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author zhangboqing
 * @date 2018/4/2
 */
@Repository
public interface UserRepository extends BaseMapper<UserRepository> {


    @Select("select * from user where user_name = #{userName}")
    User findByUserName(@Param("userName") String userName);
}
