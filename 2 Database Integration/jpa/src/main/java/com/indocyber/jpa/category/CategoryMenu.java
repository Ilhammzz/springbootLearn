package com.indocyber.jpa.category;

import com.indocyber.jpa.menus.Menu;
import com.indocyber.jpa.product.Product;
import com.indocyber.jpa.shared.input.InputService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CategoryMenu implements Menu {

    private final CategoryRepository categoryRepository;
    private final InputService inputService;

    @Override
    public void run() {
        while (true) {
            var menu = """
                    == Category Menu ==
                    1.\t Read All Categories
                    2.\t Insert new Category
                    3.\t Update existing Category
                    4.\t Delete Category
                    5.\t Back to Main Menu
                    """;
            System.out.println(menu);

            var choiceInput = inputService.readInt("Pick a number: ", 1, 5);

            switch (choiceInput) {
                case 1 -> readCategoriesMenu();
                case 2 -> insertCategory();
                case 3 -> updateCategory();
                case 4 -> deleteCategory();
                case 5 -> {
                    return;
                }
            }
        }
    }

    private void readCategoriesMenu() {
        var menu = """
                Choose read all method:
                1. Basic Select All
                2. Pagination
                3. Back to Category Menu
                """;
        System.out.println(menu);

        var choiceInput = inputService.readInt("Pick a number: ", 1, 3);
        switch (choiceInput) {
            case 1 -> categoriesFindAll();
            case 2 -> categoriesPagination();
            case 3 -> {
                return;
            }
        }
    }

    private void categoriesFindAll() {
        categoryRepository.findAll().forEach(System.out::println);
    }

    private void categoriesPagination() {
        int pageSize = inputService.readInt("How many data would you like to see at one time? ");
        Pageable pageable = PageRequest.ofSize(pageSize);

        while (true) {
            var page = categoryRepository.findAll(pageable);
            page.forEach(System.out::println);

            if (!page.hasNext()) break;

            String prompt = "Type 'Y' to go to the next page, or 'N' to return to menu";
            if (!inputService.readBoolean(prompt, "Y", "N")) {
                break;
            }
            pageable = page.nextPageable();
        }
    }


    private void insertCategory() {
        try {
            Category category = new Category();
            category.setId(null);
            category.setName(inputService.readString("Name: "));
            if (inputService.readBoolean("Set description? Y/N : ", "Y", "N")) {
                category.setDescription(inputService.readString("Description: "));
            }
            chooseInsertionDemo(category);
            System.out.println("Insert successful");
        } catch (Exception e) {
            System.out.println("New category failed to insert. Try Again");
        }
    }

    private void chooseInsertionDemo(Category category) {
        var menu = """
                Would you like to add ONE product to this category? Y/N
                *note this is a demo for cascade AND fetch eager.
                """;

        var isAnswerY = inputService.readBoolean(menu, "Y", "N");
        if (isAnswerY) {
            var product = new Product();
            product.setName(inputService.readString("Product Name: "));
            product.setPrice(new BigDecimal(inputService.readInt("Product Price: ")));
            product.setUnitsInStock(inputService.readInt("Product Stock: "));
            product.setCategory(category);

            if (category.getProducts() == null) {
                category.setProducts(new ArrayList<>());
            }
            category.getProducts().add(product);
        }
        //notice we don't need to save it from product repository too. because it cascaded
        categoryRepository.save(category);

        //this is a demo for fetch type eager. if we don't use eager, then by default is lazy
        if (category.getProducts() != null) category.getProducts().forEach(System.out::println);
    }


    private void updateCategory() {
        while (true) {
            try {
                var category = getCategory();
                category.setName(inputService.readString("Name: "));
                if (inputService.readBoolean("Set description? Y/N : ", "Y", "N")) {
                    category.setDescription(inputService.readString("Description: "));
                }
                categoryRepository.save(category);
                System.out.println("Update successful");
                return;
            } catch (EntityNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Failed to update category. Try Again");
                return;
            }
        }
    }


    private void deleteCategory() {
        try {
            var category = getCategory();
            categoryRepository.delete(category);
            System.out.println("Delete successful");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private Category getCategory() throws EntityNotFoundException {
        var id = inputService.readInt("Input category ID: ");
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No category found with that ID. Try Again"));
    }
}
