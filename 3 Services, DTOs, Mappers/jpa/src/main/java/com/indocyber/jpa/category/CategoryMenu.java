package com.indocyber.jpa.category;

import com.indocyber.jpa.category.dto.UpsertCategoryRequest;
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

    private final CategoryService categoryService;
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
                    5.\t Run Procedure
                    6.\t Back to Main Menu
                    """;
            System.out.println(menu);

            var choiceInput = inputService.readInt("Pick a number: ", 1, 5);

            switch (choiceInput) {
                case 1 -> findAllCategories();
                case 2 -> insertCategory();
                case 3 -> updateCategory();
                case 4 -> deleteCategory();
                case 5 -> categoryProcedure();
                case 6 -> {
                    return;
                }
            }
        }
    }

    private void findAllCategories() {
        try {
            int pageNumber = inputService.readInt("page number: ");
            int size = inputService.readInt("page size: ");
            String name = inputService.readString("filter by name (can be left blank): ");

            var pageData = categoryService.findAllCategories(pageNumber, size, name);
            do {
                pageData.forEach(System.out::println);

                boolean nextPage = inputService.readBoolean("Next page?", "y", "n");
                if (nextPage) {
                    pageData = categoryService.findAllCategories(++pageNumber, size, name);
                }

            } while (pageData.hasNext());
            System.out.println("All data has been shown");
            System.out.println("Back to menu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertCategory() {
        try {
            String name = inputService.readString("Name: ");
            String description = null;
            if (inputService.readBoolean("Add description?", "y", "n")) {
                description = inputService.readString("Description: ");
            }
            var savedCategory = categoryService.insertCategory(new UpsertCategoryRequest(name, description));
            System.out.println("category saved");
            System.out.println(savedCategory);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateCategory() {
        try {
            var id = inputService.readInt("Category ID: ");
            var dto = categoryService.findCategoryById(id);

            String name = dto.getName();
            System.out.println("name: %s".formatted(name));
            if (inputService.readBoolean("Edit name?", "y", "n")) {
                name = inputService.readString("Name: ");
            }

            String description = dto.getDescription();
            System.out.println("description: %s".formatted(description));
            var descriptionPrompt = "%s description?".formatted(description == null ? "Add" : "Edit");
            if (inputService.readBoolean(descriptionPrompt, "y", "n")) {
                description = inputService.readString("Description: ");
            }

            var savedCategory = categoryService.updateCategory(id, new UpsertCategoryRequest(name, description));
            System.out.println("category saved");
            System.out.println(savedCategory);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteCategory() {
        try {
            var id = inputService.readInt("Category ID: ");
            categoryService.deleteCategoryById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void categoryProcedure() {
        categoryService.pricePerCategories().forEach(System.out::println);
    }
}
