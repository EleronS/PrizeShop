package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Toy extends  Prize{
    private ArrayList<Prize> prizes;
    private Prize p;

    public Toy() {
        super();
        this.prizes = new ArrayList<>();
    }

    public Toy(String name) {
        super(name);
    }

    public Toy(String name, int count, float weight) {
        super(name, count, weight);
    }

    // Добавление новой игрушки в магазин
    public void addNewToy(Prize prize) {
        // Добавляем новую игрушку в список призов
        prizes.add(prize);
    }
    public void changeWeight(int index, int newWeight) {
        Prize prize = prizes.get(index);
        if (newWeight >= 1 && newWeight <= 100) {
            prize.setWeight(newWeight);
        } else {
            throw new IllegalArgumentException("Frequency must be between 1 and 100.");
        }
        // Обновляем общий вес всех игрушек
        float totalWeight = 0.0f;
        for (Prize p : prizes) {
            totalWeight += p.getWeight();
        }
        totalWeight -= newWeight;
        p.setTotalWeight(totalWeight);
    }
    public Prize getRandomPrize() {
        return prizes.get((int) (Math.random() * prizes.size()));
    }

    // Получение общего веса всех игрушек в списке
    public float getTotalWeight() {
        float sum = 0f;
        for (Prize p : prizes) {
            sum += p.getTotalWeight();
        }
        return sum;
    }

    // Загрузка игрушек из файла
    public void loadToys() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("toys.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Prize prize = new Prize(line);
            this.addNewToy(prize);
        }
        scanner.close();
    }

    // Вычисление количества игрушек, выпавших в розыгрыше
    public int getPrizeCount() {
        int count = 0;
        for (Prize prize : prizes) {
            if (prize.isWon()) {
                count++;
            }
        }
        return count;
    }
}