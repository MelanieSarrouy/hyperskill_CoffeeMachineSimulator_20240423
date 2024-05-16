package machine;

import java.util.Scanner;

public class Machine {
    int water;
    int milk;
    int beans;
    int cups;
    int money;

    Machine (int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }

    public void displayMachineState() {
        System.out.println("The coffee machine has :");
        System.out.println(this.water + " ml of water");
        System.out.println(this.milk + " ml of milk");
        System.out.println(this.beans + " g of coffee beans");
        System.out.println(this.cups + " disposable cups");
        System.out.println("$" + this.money + " of money");
        System.out.println();
    }

    public void makeAChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select an action : 1 - buy, 2 - fill, 3 - take, 4 - remaining, 5 - exit ");
        String action = scanner.nextLine();
        switchActions(action);
    }

    private void switchActions (String action) {
        Scanner scanner = new Scanner(System.in);
        switch (action) {
            case "buy":
                System.out.println("What do you want to buy ? 1 - espresso, 2 - latte, 3 - cappuccino:");
                String drink = scanner.next();
                updateMachine(drink);
                System.out.println();
                makeAChoice();
                break;
            case "fill":
                System.out.println("Write how many ml of water you want to add:");
                this.water +=  scanner.nextInt();
                System.out.println("Write how many ml of milk you want to add:");
                this.milk += scanner.nextInt();
                System.out.println("Write how many grams of coffee beans you want to add:");
                this.beans += scanner.nextInt();
                System.out.println("Write how many disposable cups you want to add:");
                this.cups += scanner.nextInt();
                System.out.println();
                makeAChoice();
                break;
            case "take":
                System.out.println("I gave you $" + this.money);
                this.money = 0;
                System.out.println();
                makeAChoice();
                break;
            case "remaining":
                displayMachineState();
                makeAChoice();
                break;
            case "exit":
                scanner.close();
                break;
            default:
        }
    }

    public void updateMachine(String drink) {
        Drink espresso = new Drink(250,0,16,4);
        Drink latte = new Drink(350,75,20,7);
        Drink cappuccino = new Drink(200,100,12,6);
        switch (drink) {
            case "1":
                if (testStockStatus(espresso)) {
                    this.water -= espresso.water;
                    this.beans -= espresso.beans;
                    this.cups--;
                    this.money += espresso.cost;
                }
                break;
            case "2":
                if (testStockStatus(latte)) {
                    this.water -= latte.water;
                    this.milk -= latte.milk;
                    this.beans -= latte.beans;
                    this.cups--;
                    this.money += latte.cost;
                }
                break;
            case "3":
                if (testStockStatus(cappuccino)) {
                    this.water -= cappuccino.water;
                    this.milk -= cappuccino.milk;
                    this.beans -= cappuccino.beans;
                    this.cups--;
                    this.money += cappuccino.cost;
                }
                break;
            default:
        }
    }

    public boolean testStockStatus(Drink drink) {
        boolean isOK;
        int supply = this.water - drink.water < 0 ? 1 : this.milk - drink.milk < 0 ? 2 : this.beans - drink.beans < 0 ? 3 : this.cups == 0 ? 4 : 0;
        isOK = supply == 0;
        if (isOK) {
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            System.out.print("Sorry, not enough ");
            switch (supply) {
                case 1:
                    System.out.println("water");
                    break;
                case 2:
                    System.out.println("milk");
                    break;
                case 3:
                    System.out.println("coffee beans");
                    break;
                case 4:
                    System.out.println("cups");
                    break;
                default:
            }
        }
        return  isOK;
    }

}