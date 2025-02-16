package ua.nau.lab1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CaesarCipher {
    private static final String ALPHABET = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";
    private static final int ALPHABET_SIZE = 33;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть ваше ПІБ:");
        String pib = scanner.nextLine();

        String plaintext = "Я, " + pib + ", студент університету НАУ";
        System.out.println("Відкритий текст: " + plaintext);

        System.out.println("Введіть ключ (k) для шифру Цезаря:");
        int key = scanner.nextInt();

        // Шифрування
        String encrypted = encrypt(plaintext, key);
        System.out.println("Зашифрований текст: " + encrypted);

        // Дешифрування
        String decrypted = decrypt(encrypted, key);
        System.out.println("Розшифрований текст: " + decrypted);

        // Запис у файли
        writeToFile("encrypted.txt", encrypted);
        writeToFile("decrypted.txt", decrypted);
    }

    public static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();

        for (char character : text.toCharArray()) {
            if (ALPHABET.indexOf(character) != -1) {
                int position = ALPHABET.indexOf(character);
                int newPosition = (position + key) % ALPHABET_SIZE;
                result.append(ALPHABET.charAt(newPosition));
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }

    public static String decrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();

        for (char character : text.toCharArray()) {
            if (ALPHABET.indexOf(character) != -1) {
                int position = ALPHABET.indexOf(character);
                int newPosition = (position - key + ALPHABET_SIZE) % ALPHABET_SIZE;
                result.append(ALPHABET.charAt(newPosition));
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }

    private static void writeToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
            System.out.println("Текст записано у файл: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка при записі у файл: " + e.getMessage());
        }
    }
}
