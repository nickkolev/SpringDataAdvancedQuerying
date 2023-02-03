package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByNameStartingWith(String start);

    List<Ingredient> findByNameInOrderByPrice(List<String> names);

    int deleteByName(String name);

    @Query("UPDATE Ingredient i SET i.price = i.price + i.price * :multiplier")
    @Modifying
    void increasePriceByPercent(@Param("multiplier") BigDecimal percent);

    @Query("UPDATE Ingredient i SET i.price = :newPrice WHERE i.name IN :ingredientsNames")
    @Modifying
    void updateIngredientsPriceInGivenList(BigDecimal newPrice, List<String> ingredientsNames);
}
