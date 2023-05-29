package com.shoppinglistapp.dto;

import com.shoppinglistapp.repository.ShoppingListEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
public class ShoppingListList {


    private final List<ShoppingListItem> item;

    public ShoppingListList(List<ShoppingListEntity> entities) {
        item = entities.stream().map(ShoppingListItem::fromEntity).toList();
    }

    @Getter
    @AllArgsConstructor
    public static class ShoppingListItem {

        private final Long id;
        private final String productName;
        private final double amount;
        private final TypeEnum typeEnum;

        public static ShoppingListItem fromEntity(ShoppingListEntity entity) {
            return new ShoppingListItem(
                    entity.getId(),
                    entity.getProductName(),
                    entity.getAmount(),
                    TypeEnum.fromDBValue(entity.getTypeEnum())
            );
        }

    }
}