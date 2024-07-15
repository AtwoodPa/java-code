package com.oi.db.controller;

import com.oi.db.entity.Page;
import com.oi.db.entity.User;
import com.oi.db.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author supanpan
 * @date 2024/07/15
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public Page<User> getUsers(@RequestParam(defaultValue = "1") int pageNumber,
                               @RequestParam(defaultValue = "10") int pageSize) {
        return userService.getUserList(pageNumber, pageSize);
    }
}
