package org.example;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Prize {
    String name;
    int count;
    float weight;

    public Prize(String name) {
        setName(name);
        setCount(0);
        setWeight(100);
    }

    public Prize(String name, int count, float weight) {
        this.name = name;
        this.count = count;
        this.weight = weight;
    }

    public Prize() {

    }


    // Установка имени игрушки
    public void setName(String name) {
        this.name = name;
    }

    // Увеличение счетчика выпадения
    public void incrementCount() {
        setCount((int) Math.round((getCount() + 1) * getWeight()));
    }

    // Проверка выпадения
    boolean isWon() {
        return getCount() >= getWeight();
    }

    // Установка общего веса игрушки
    void setTotalWeight(float totalWeight) {
        weight = (float) (Math.floor(totalWeight * 10) / 10);
    }

    // Установка счетчика выпадения и веса
    void updateWeight(float newWeight) throws IllegalArgumentException {
        if (getCount() < newWeight || getCount() > (1 - newWeight)) {
            throw new IllegalArgumentException(
                    "Frequency must be between " + getCount() + " and " + (1 - getCount())
            );
        } else if (newWeight < 1 ||newWeight > 100 || isWon()) {
            throw new IllegalArgumentException("Invalid frequency.");
        } else {
            incrementCount();
            setWeight(getWeight() * newWeight / 100);
        }
    }

    // Получить имя игрушки
    String getName() {
        return this.name;
    }

    // Установить имя игрушки и счетчик выпадения
    void reset() {
        setName("Unnamed Toy");
        setCount(1);
    }

    // Получить общее количество игрушек, выпавших за все розыгрыши
    float getTotalWeight() {
        return weight;
    }

    // Сохранить игрушку в файл
    void save() throws IOException {
        FileWriter writer = new FileWriter("toys.txt");
        writer.write(getName());
        writer.flush();
        writer.close();
    }

    // Считать игрушку из файла
    void load() throws IOException {
        String fileName = getName();
        FileReader reader = new FileReader(fileName);
        try {
            reader.skip(reader.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reader.read();
        reader.close();
        setName(fileName);
        setCount((int) 1f);
    }

    // Задать счетчик выпадения и общий вес игрушки
    void init(float count, float weight) {
        incrementCount();
        setTotalWeight(weight);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Prize{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", weight=" + weight +
                '}';
    }
}
