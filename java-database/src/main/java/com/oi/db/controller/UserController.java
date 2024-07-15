package com.oi.db.controller;

import com.oi.db.entity.Page;
import com.oi.db.entity.User;
import com.oi.db.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author supanpan
 * @date 2024/07/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    /**
     * 分页查询
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/pageList")
    public Page<User> getUsers(@RequestParam(defaultValue = "1") int pageNumber,
                               @RequestParam(defaultValue = "10") int pageSize) {
        return userService.getUserList(pageNumber, pageSize);
    }

    /**
     * 分页查询 优化limit
     * http://localhost:8080/user/optimized?lastId=10&pageSize=5
     * http://localhost:8080/user/optimized?pageSize=10
     * @param lastId
     * @param pageSize
     * @return
     */
    @GetMapping("/optimized")
    public Page<User> getUsersOptimized(@RequestParam(required = false) Integer lastId,
                                        @RequestParam(defaultValue = "10") int pageSize) {
        return userService.getUsersOptimized(lastId, pageSize);
    }
}
