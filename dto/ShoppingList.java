package com.shoppinglistapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ShoppingList {


//        private final Long id;
        private final String productName;
        private final double amount;
        private final TypeEnum typeEnum;


    }
