import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class main {
    public static int TOYS_COUNT = 10;
    public static ArrayList<Toy> allToys = new ArrayList<>();
    public static Stack<String> prizeToys = new Stack<String>();

    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        Integer operation = 0;
        while (operation != 7) {
            System.out.printf(
                    "===========================================\nВыберите операцию:\n1.Показать все игрушки\n2.Добавить игрушку\n3.Изменить вес игрушки\n4.Добавить игрушку в список призовых\n5.Показать список призовых игрушек в очереди\n6.Выдать призовую игрушку\n7.Выход\n===========================================\n->");
            operation = sc.nextInt();
            switch (operation) {

                case 1:
                    System.out.println("Показать все игрушки");
                    showToysInShop();
                    break;
                case 2:
                    System.out.println("Добавить игрушку");
                    addNewToy();
                    break;
                case 3:
                    System.out.println("Изменить вес игрушки");
                    changeWeight();
                    break;
                case 4:
                    System.out.println("Добавить игрушку в список призовых");
                    addToPrizeToys();
                    break;
                case 5:
                    System.out.println("Показать список призовых игрушек в очереди");
                    showPrizeToysList();
                    break;
                case 6:
                    System.out.println("Выдать призовую игрушку");
                    giveOutPrizeToy();
                    break;
                case 7:
                    break;
                default:
                    break;
            }
        }
        sc.close();

    }

    private static void init() {
        for (int i = 0; i < TOYS_COUNT; i++) {
            allToys.add(new Toy(getName(), ThreadLocalRandom.current().nextInt(10)));
        }
    }

    private static String getName() {
        return Names.values()[ThreadLocalRandom.current().nextInt(Names.values().length)].toString();
    }

    private static void changeWeight() {
        System.out.printf("Выберите ID игрушки для изменения шанса выпадения:");
        Scanner sc = new Scanner(System.in);
        Boolean checkToy = false;
        while (checkToy == false) {
            Integer toyId = sc.nextInt();
            for (int i = 0; i < allToys.size(); i++) {
                if (toyId == allToys.get(i).getId()) {
                    Integer newWeight = -1;
                    while (newWeight < 0 || newWeight > 100) {
                        System.out.printf("Введите новый вес от 0 до 100");
                        newWeight = sc.nextInt();
                    }
                    allToys.get(i).setChance(newWeight);
                    checkToy = true;
                    break;
                }
            }
        }
    }

    private static void showToysInShop() {
        for (int i = 0; i < allToys.size(); i++) {
            System.out.println(allToys.get(i).toString());
        }
    }

    private static void addNewToy() {
        allToys.add(new Toy(getName(), ThreadLocalRandom.current().nextInt(10)));
    }

    private static void addToPrizeToys() {
        System.out.printf("Выберите ID игрушки для добавления в список призовых:");
        Scanner sc = new Scanner(System.in);
        Boolean checkToy = false;
        while (checkToy == false) {
            Integer toyId = sc.nextInt();
            for (int i = 0; i < allToys.size(); i++) {
                if (toyId == allToys.get(i).getId()) {
                    if (allToys.get(i).getCount() > 0) {
                        allToys.get(i).decCount();
                        prizeToys.push(allToys.get(i).getName());
                        System.out.println("Игрушка добавлена в список призовых!");
                    } else {
                        System.out.println("Игрушек нет на складе!");
                    }

                    checkToy = true;
                    break;
                }
            }
        }
    }

    private static void showPrizeToysList() {
        for (int i = 0; i < prizeToys.size(); i++) {
            System.out.println(i + "->" + prizeToys.get(i));
        }
    }

    private static void giveOutPrizeToy() {
        if (prizeToys.size() > 0) {
            log(prizeToys.remove(0));

        } else {
            System.out.println("Нет игрушек в списке призовых!");
        }
    }

    private static void log(String toyName) {
        try (BufferedWriter log = new BufferedWriter(new FileWriter("log.txt", StandardCharsets.UTF_8, true));) {
            log.write(toyName + " Выдан победителю " + LocalDateTime.now() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
