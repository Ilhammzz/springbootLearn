package com.indocyber.jpa.product;

import com.indocyber.jpa.menus.Menu;
import com.indocyber.jpa.shared.input.InputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMenu implements Menu {
    private final InputService inputService;
    private final ProductRepository productRepository;
    private final ProductService productService; //digunakan hanya untuk contoh @Procedure

    @Override

    public void run() {
        while (true) {
            var menu = """
                    === Product ====
                    1. Find Active Products by Category Name
                    2. Exchange Stock Order
                    3. Clear Stock For Discontinued (Bulk Update)
                    4. Back to Main Menu
                    """;
            System.out.println(menu);

            var choiceInput = inputService.readInt("Pick a number: ", 1, 4);

            switch (choiceInput) {
                case 1 -> findActiveProductsMenu();
                case 2 -> exchangeStockOrderMenu();
                case 3 -> clearStockMenu();
                case 4 -> {
                    return;
                }
            }
        }
    }

    private void findActiveProductsMenu() {
        while (true) {
            System.out.println("""
                    Pick a demo:
                    1. Derived Query
                    2. JPQL
                    3. Native SQL (SQL Server)
                    4. Procedure
                    5. Back to Product Menu
                    """);
            var choiceInput = inputService.readInt("Pick a number: ", 1, 5);

            switch (choiceInput) {
                case 1 -> derivedQueryDemo();
                case 2 -> jpqlDemo();
                case 3 -> nativeQueryDemo();
                case 4 -> procedureSelectDemo();
                case 5 -> {
                    return;
                }
            }
        }
    }

    private void exchangeStockOrderMenu() {
        while (true) {
            System.out.println("""
                    (update 'on ordered' items into new stocks)
                    Pick a demo:
                    1. @Modifying Demo
                    2. Procedure Demo
                    3. Back to Product Menu
                    """);
            var choiceInput = inputService.readInt("Pick a number: ", 1, 3);

            switch (choiceInput) {
                case 1 -> exchangeStockOrderModifyingDemo();
                case 2 -> exchangeStockOrderProcedureDemo();
                case 3 -> {
                    return;
                }
            }
            productRepository.findAll().forEach(System.out::println);
        }
    }

    private void clearStockMenu() {
        while (true) {
            System.out.println("""
                    (change all products which are discontinued to no longer discontinue)
                    Pick a demo for bulk update :
                    1. @Modifying demo
                    2. Back to Product Menu
                    """);
            var choiceInput = inputService.readInt("Pick a number: ", 1, 2);

            switch (choiceInput) {
                case 1 -> clearStockModifyingDemo();
                case 2 -> {
                    return;
                }
            }
            productRepository.findAll().forEach(System.out::println);
        }
    }

    //SELECT DEMO
    private void derivedQueryDemo() {
        var categoryName = inputService.readString("Category Name: ");
        productRepository.findByCategoryNameAndDiscontinueFalse(categoryName)
                .forEach(System.out::println);
    }

    private void jpqlDemo() {
        var categoryName = inputService.readString("Category Name: ");
        productRepository.findActiveProductsByCategoryName(categoryName)
                .forEach(System.out::println);
    }

    private void nativeQueryDemo() {
        var categoryName = inputService.readString("Category Name: ");
        productRepository.findActiveProductsByCategoryNameNative(categoryName)
                .forEach(System.out::println);
    }

    private void procedureSelectDemo() {
        var categoryName = inputService.readString("Category Name: ");
        productService.storedProcedure(categoryName)
                .forEach(System.out::println);
    }

    //EXCHANGE
    private void exchangeStockOrderModifyingDemo() {
        var productId = inputService.readInt("Product ID: ");
        productRepository.exchangeStockFromOnOrder(productId);
    }

    private void exchangeStockOrderProcedureDemo() {
        var productId = inputService.readInt("Product ID: ");
        productRepository.callExchangeStockOnOrder(productId);
    }

    //BULK INSERT
    private void clearStockModifyingDemo() {
        productRepository.clearStockForDiscontinued();
    }
}
