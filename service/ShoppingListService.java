package com.shoppinglistapp.service;

import com.shoppinglistapp.dto.ShoppingList;
import com.shoppinglistapp.dto.ShoppingListList;
import com.shoppinglistapp.dto.TypeEnum;
import com.shoppinglistapp.repository.ShoppingListEntity;
import com.shoppinglistapp.repository.ShoppingListRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ShoppingListService {
    private final ShoppingListRepository repository;


//    public Long create(ShoppingList product){
//        ShoppingListEntity entity = mapToEntity(product);
//        repository.save(entity);
//        return entity.getId();
//    }

    @Transactional
    public boolean update(Long id, ShoppingList list){
        Optional<ShoppingListEntity> entityWrapper = repository.findById(id);

        if (entityWrapper.isPresent()) {
            updateEntity(entityWrapper.get(), list);
            return true;
        }
        return false;
    }

    public ShoppingList getProduct(Long id){
        ShoppingListEntity entity = repository.getReferenceById(id);
        return mapFromEntity(entity);
    }

    public ShoppingList saveProduct(ShoppingList product){
        ShoppingListEntity entity = new ShoppingListEntity();
        entity.setProductName(product.getProductName());
        entity.setAmount(product.getAmount());
        entity.setTypeEnum(product.getTypeEnum().getDbValue());

        ShoppingListEntity savedEntity = repository.save(entity);

        return mapFromEntity(savedEntity);
    }

   public ShoppingListList getList(){
        List<ShoppingListEntity> entities = repository.findAll();
        return new ShoppingListList(entities);
   }

    public void deleteProduct(Long id){
        repository.deleteById(id);
    }

    private ShoppingList mapFromEntity (ShoppingListEntity entity){
        return new ShoppingList(
                entity.getProductName(),
                entity.getAmount(),
                TypeEnum.fromDBValue(entity.getTypeEnum())
        );
    }

    private ShoppingListEntity mapToEntity(ShoppingList list){
        return new ShoppingListEntity(
                null,
                list.getProductName(),
                list.getAmount(),
                list.getTypeEnum().getDbValue()
        );
    }

    private void updateEntity(ShoppingListEntity entity, ShoppingList list){
        entity.setProductName(list.getProductName());
        entity.setAmount(list.getAmount());
        entity.setTypeEnum(list.getTypeEnum().getDbValue());
    }
}
