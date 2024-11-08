package com.oi.db.service;

import com.oi.db.entity.Page;
import com.oi.db.entity.User;
import com.oi.db.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务
 *
 * @author supanpan
 * @date 2024/07/15
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    /**
     * 获取用户列表 - 分页实现
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<User> getUserList(int pageNumber, int pageSize) {
        // 计算偏移量
        int offset = (pageNumber - 1) * pageSize;
        List<User> userList = userMapper.selectUsers(offset, pageSize);
        // 获取总记录数
        int total = userMapper.countUsers();
        return new Page<>(userList, pageNumber, pageSize, total);
    }

    /**
     * 获取用户列表 - 优化limit实现
     * @param lastId
     * @param pageSize
     * @return
     */
    public Page<User> getUsersOptimized(Integer lastId, int pageSize) {
        List<User> users;
        if (lastId == null) {
            users = userMapper.selectUsersOptimized(pageSize);
        } else {
            users = userMapper.selectUsersOptimizedAfter(lastId, pageSize);
        }
        int totalRecords = userMapper.countUsers();
        return new Page<>(users, lastId == null ? 1 : -1, pageSize, totalRecords);
    }
}
