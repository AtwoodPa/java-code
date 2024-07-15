package com.oi.db.mapper;

import com.oi.db.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author supanpan
 * @date 2024/07/15
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectUserById(@Param("id") int id);

    @Select("SELECT * FROM user LIMIT #{offset}, #{pageSize}")
    List<User> selectUsers(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM user")
    int countUsers();

    @Select("SELECT u.* FROM user u INNER JOIN ( SELECT id FROM user ORDER BY id LIMIT #{pageSize}) lim ON u.id = lim.id")
    List<User> selectUsersOptimized(int pageSize);

    @Select("SELECT u.* FROM user u INNER JOIN ( SELECT id FROM user WHERE id > #{lastId} ORDER BY id LIMIT #{pageSize}) lim ON u.id = lim.id")
    List<User> selectUsersOptimizedAfter(Integer lastId, int pageSize);
}
