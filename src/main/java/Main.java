//public class Main {
//    public static void main(){
/// /        Product product = new Product(123, "Apple Duo", 2000, "Your device is broken, sorry!");
/// /        product.setId(246);
/// /
/// /        System.out.println("Product #" + product.getId());
/// /        System.out.println(product);
//        Category electronics = new Category(1, "Електроніка");
//        Category smartphones = new Category(2, "Смартфони");
//        Category accessories = new Category(3, "Аксесуари");
//
//        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
//        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
//        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);
//
//        // Виведення інформації про товари
//        System.out.println(product1);
//        System.out.println(product2);
//        System.out.println(product3);
//
//        Cart cart = new Cart();
//
//        cart.addProduct(product1);
//        cart.addProduct(product2);
//        cart.addProduct(product3);
//
//        System.out.println(cart);
//
//        cart.removeProduct(product3);
//
//        System.out.println("\nПісля видалення товару:");
//        System.out.println(cart);
//
//
//    }
//}

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        List<Product> products = List.of(product1, product2, product3);
        List<Order> orders = new ArrayList<>();

        Cart cart = new Cart();
        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Пошук товарів за назвою або категорією");
            System.out.println("3 - Додати товар до кошика");
            System.out.println("4 - Видалити товар з кошика");
            System.out.println("5 - Переглянути кошик");
            System.out.println("6 - Зробити замовлення");
            System.out.println("7 - Історія замовлень");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println(product1);
                    System.out.println(product2);
                    System.out.println(product3);
                    break;
                case 2:
                    System.out.println("Пошук: ");
                    String search = scanner.nextLine();

                    List<Product> result = new ArrayList<>();

                    for (Product p : products) {
                        if (p.getName().toLowerCase().contains(search) || p.getCategory().getName().toLowerCase().contains(search)) {
                            result.add(p);
                        }
                    }

                    if (result.size() == 0) {
                        System.out.println("Нічого не знайдено.");
                        break;
                    }

                    System.out.println("Знайдено результати пошуку:");
                    for (Product p : result) {
                        System.out.println(p);
                    }
                    break;
                case 3:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int id = scanner.nextInt();

                    if (id == 1) cart.addProduct(product1);
                    else if (id == 2) cart.addProduct(product2);
                    else if (id == 3) cart.addProduct(product3);
                    else System.out.println("Товар з таким ID не знайдено");
                    break;
                case 4:
                    System.out.println("Введіть ID товару для видалення з кошика:");
                    int deletedId = scanner.nextInt();

                    if (deletedId == 1) cart.removeProduct(product1);
                    else if (deletedId == 2) cart.removeProduct(product2);
                    else if (deletedId == 3) cart.removeProduct(product3);
                    else System.out.println("Товар з таким ID не знайдено");
                    break;
                case 5:
                    System.out.println(cart);
                    break;
                case 6:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orders.add(order);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;
                case 7:
                    System.out.println("Історія замовлень:");
                    for (Order o : orders) {
                        System.out.println(o);
                    }
                    break;
                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}