package com.sda.app.dto;

import com.sda.app.entity.Item;
import lombok.Data;

import java.util.List;
@Data
public class CartDto {
    private Integer id;
    private Integer userId;
    private List<Item> items;
}
