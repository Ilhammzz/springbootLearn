package com.indocyber.jpa.shared.input;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class InputService {
    private final Scanner scanner = new Scanner(System.in);

    public String readString(String prompt) {
        String value;
        while (true) {
            try {
                System.out.print(prompt + " ");
                value = scanner.nextLine().trim();
                return value;
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    public Integer readInt(String prompt) {
        Integer value;
        while (true) {
            try {
                System.out.print(prompt + " ");
                value = Integer.parseInt(scanner.nextLine().trim());
                return value;

            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    public Integer readInt(String prompt, int min, int max) {
        while (true) {
            Integer value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.printf("Input must be between %s to %s. Try again\n", min, max);
        }
    }

    public Boolean readBoolean(String prompt, String trueInput, String falseInput) {
        while (true) {
            String input = readString(prompt).trim();
            if (input.isEmpty()) {
                System.out.printf("Please input '%s' or '%s'. Try Again\n", trueInput, falseInput);
                continue;
            }
            if (input.equalsIgnoreCase(trueInput)) return true;
            if (input.equalsIgnoreCase(falseInput)) return false;
            System.out.printf("Please input '%s' or '%s'. Try Again\n", trueInput, falseInput);
        }
    }

}
