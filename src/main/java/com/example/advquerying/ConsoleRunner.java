package com.example.advquerying;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private ShampooRepository shampooRepository;

    private IngredientRepository ingredientRepository;

    @Autowired
    public ConsoleRunner(ShampooRepository shampooRepository, IngredientRepository ingredientRepository) {
        this.shampooRepository = shampooRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<String> ingredientsNames = new ArrayList<>();

        ingredientsNames.add("Apple");
        ingredientsNames.add("Nettle");
        ingredientsNames.add("Aloe Vera");

        this.ingredientRepository.updateIngredientsPriceInGivenList(BigDecimal.valueOf(1.1), ingredientsNames);

    }

    private void query8() {
        List<Shampoo> shampoos = this.shampooRepository.findByIngredientCountLessThan(2);

        shampoos.forEach(System.out::println);
    }

    private void query7() {
        Set<String> ingredients = new LinkedHashSet<>();

        ingredients.add("Berry");
        ingredients.add("Mineral-Collagen");

        List<Shampoo> shampoos = this.shampooRepository.findByIngredientsNames(ingredients);

        for (Shampoo shampoo : shampoos) {
            System.out.println(shampoo);
        }
    }

    private void query6() {
        int count = this.shampooRepository.countByPriceLessThan(BigDecimal.valueOf(8.50));

        System.out.println(count);
    }

    private void query5() {
        List<String> names = new ArrayList<>();
        names.add("Lavender");
        names.add("Herbs");
        names.add("Apple");

        List<Ingredient> ingredients = this.ingredientRepository.findByNameInOrderByPrice(names);

        ingredients.forEach(System.out::println);
    }

    private void query4() {
        List<Ingredient> ingredients = this.ingredientRepository.findByNameStartingWith("M");

        ingredients.forEach(System.out::println);
    }

    private void query3() {
        List<Shampoo> shampoos = this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc(BigDecimal.valueOf(5));

        shampoos.stream().forEach(System.out::println);
    }

    private void query2() {
        List<Shampoo> shampoos = this.shampooRepository.findBySizeOrLabelIdOrderByPriceAsc(Size.MEDIUM, 10);

        shampoos.stream().forEach(System.out::println);
    }

    private void query1() {
        List<Shampoo> shampoos = this.shampooRepository.findBySizeOrderById(Size.MEDIUM);

        shampoos.stream().forEach(System.out::println);
    }


}
