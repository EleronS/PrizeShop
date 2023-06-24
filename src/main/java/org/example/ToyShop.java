package org.example;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ToyShop {
    public static void main(String[] args) throws FileNotFoundException {
        Toy shop = new Toy();
        shop.loadToys();
        int prizeCount = shop.getPrizeCount();
        for (int i = 0; i < prizeCount; i++) {
            System.out.println("Розыгрыш: " + i + " - " + shop.getRandomPrize());
        }
    }
}
