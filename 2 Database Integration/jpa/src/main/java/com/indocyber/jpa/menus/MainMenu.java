package com.indocyber.jpa.menus;

import com.indocyber.jpa.category.CategoryMenu;
import com.indocyber.jpa.product.ProductMenu;
import com.indocyber.jpa.shared.input.InputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MainMenu implements Menu {
    private final CategoryMenu categoryMenu;
    private final InputService inputService;
    private final ProductMenu productMenu;

    @Override
    public void run() {
        while (true) {
            var menu = """
                    == Main Menu ==
                    1. Category Menu
                    2. Product Menu
                    3. Exit
                    """;
            System.out.println(menu);
            var choiceInput = inputService.readInt("Pick a number: ", 1, 3);

            switch (choiceInput) {
                case 1 -> categoryMenu.run();
                case 2 -> productMenu.run();
                case 3 -> System.exit(1);
            }
        }
    }
}
