package com.oi.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页实体
 *
 * @author supanpan
 * @date 2024/07/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page <T> {
    private List<T> records;
    private int pageNumber;
    private int pageSize;
    private int totalRecords;
}
