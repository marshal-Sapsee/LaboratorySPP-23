package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Client cli = new Client();
        Administrator admin = new Administrator();
        Order ord = new Order();
        String BlackList = new String();

        Scanner scan = new Scanner(System.in);
        int x = 0;
        String s = "";

        while (!"5".equals(s)) {
            System.out.println("1. Товаровед добавляет информацию о товаре");
            System.out.println("2. Клиент делает и оплачивает заказ на товар");
            System.out.println("3. Товаровед регистрирует продажу");
            System.out.println("4. В случае неоплаты заносит клиента в черный список");
            System.out.println("5. Выход");

            s = scan.next();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод");
            }
            switch (x) {
                case 1:
                    Scanner scanTov = new Scanner(System.in);
                    String Name = "";
                    int Price = 0;
                    System.out.println("Введите наименование товара");
                    Name = scanTov.next();
                    System.out.println("Введите цену товара");
                    Price = Integer.parseInt(scanTov.next());
                    admin.createNewProduct(Name, Price);
                    break;
                case 2:
                    System.out.println("Пользователь добавил товар в корзину");
                    System.out.println("Желаете произвести оплату?");
                    String Choise = scan.next();
                    if (Choise=="Y") ord.payment = true;
                    if (Choise=="N") ord.payment = false;
                    break;
                case 3:
                    if (ord.payment==true) System.out.println("Продажа зарегистрирована");
                    if (ord.payment==false)
                    {
                        System.out.println("Продажа не оплачена");
                        //System.out.println("Пользователь будет внесен в черный список");
                        BlackList = scan.next();
                        //if (BlackList=="Y") {
                            admin.blackclients.add(cli);
                            //System.out.println("Пользователь был внесен в черный список");
                        //}
                        //if (BlackList=="N") System.out.println("Пользователь был внесен в черный список");
                    }
                    break;
                case 4:
                    if (ord.payment==false) {
                        //System.out.println("Продажа не оплачена");
                        //if (BlackList == "Y") {
                        //    admin.blackclients.add(cli);
                            System.out.println("Пользователь был внесен в черный список");
                        //}
                        //if (BlackList == "N") System.out.println("Пользователь не был внесен в черный список");
                    }
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }

    //класс Администратор
    static class Administrator {
        private ArrayList<Product> products = new ArrayList<Product>();
        private ArrayList<Client> blackclients = new ArrayList<Client>();

        //регистрация товара и добавление неплательщиков в черный список
        public void registerOrder(Client client) {
            if (client.getOrder().isPayment() == true) {
                client.getOrder().setRegister(true);
            } else {
                blackclients.add(client);
            }
        }

        //создание новых продуктов
        public void createNewProduct(String name, int price) {
            products.add(new Product(name, price));
        }
    }

    //класс Клиент
    static class Client {

        private Order order;

        //заказать
        public void book(Order order) {
            this.order = order;
        }

        public Order getOrder() {
            return order;
        }

        //показать Заказ
        public void showOrder() {
            System.out.println("Ваш заказ: ");
            order.showOrder();
        }

        //оплатить Заказ
        public void pay() {
            if (order.isPayment() == true) {
                System.out.println("Вы уже оплатили заказ");
            } else {
                order.setPayment(true);
            }
        }

        //Забрать товар
        public void take() {
            if (order.isPayment() == false) {
                System.out.println("Вы ещё не оплатили товар");
            } else if (order.isRegister() == false) {
                System.out.println("Ваша заявка ещё не обработана");
            } else {
                System.out.println("Спасибо за покупку!");
            }
        }

    }

    //класс Заказ
    static class Order {
        private ArrayList<Product> orderlist = new ArrayList<Product>();    //продукты в заказе
        private boolean payment = false;    //оплачен ли заказ
        private boolean register = false;   //обработан ли заказ Администратором

        //добавить группу товаров к Заказу
        public void addProdToOrder(ArrayList<Product> orderlist) {
            this.orderlist = orderlist;
        }

        //добавить товар к Заказу
        public void addProdToOrder(Product product) {
            orderlist.add(product);
        }

        //показать Заказ
        public void showOrder() {
            for (Product e : orderlist) {
                System.out.println(e);
            }
        }

        //оплачен или нет
        public boolean isPayment() {
            return payment;
        }

        //оплачен (да/нет)
        public void setPayment(boolean s) {
            payment = s;
        }

        public boolean isRegister() {
            return register;
        }

        public void setRegister(boolean s) {
            register = s;
        }

    }

    //класс Товар
    static class Product {
        private String name; //Название товара
        private int price;  // Цена товара

        //конструктор
        public Product(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Название = " + name + " Стоимость = " + price;
        }
    }
}
