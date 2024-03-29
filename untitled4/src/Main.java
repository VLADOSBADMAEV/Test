import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Main {
   static Scanner scanner = new Scanner(System.in);
   static String[] products = {"Хлеб", "Яблоки", "Молоко"};
   static int[] numb = {1, 2, 3};
   static int[] prices = {100, 200, 300};
   static int[] sum = new int[]{0, 0, 0};
   static int[] count = new int[]{0, 0, 0};
    static int productNumber = 0;
    static  int productCount = 0;
    static int currentPrice = 0;
    static int sumProducts = 0;

    static File saveFile = new File("basket.txt");


    public static void main(String[] args) throws FileNotFoundException {

        Basket basket = null;
        if (saveFile.exists()) {
            basket = Basket.loadFromTxtFile(saveFile);
        } else {
            basket = new Basket(products, prices);

        }

            System.out.println("Список возможных товаров для покупки");
            for (int i = 0; i < products.length; i++) {
                System.out.println(numb[i] + " " + products[i] + " " + prices[i] + " руб/шт");
            }
            while (true) {
                System.out.println("Выберите товар и количество или введите `end`");
                String input = scanner.nextLine();
                if(input.equals("1")){
                }
                System.out.println(input);
                if ("end".equals(input)) {
                    System.out.println("Программа завершена!");
                    break;

                }
                String[] parts = input.split(" ");
                String a = parts[0];
                productNumber = Integer.parseInt(a) - 1;
                String b = parts[1];
                currentPrice = prices[productNumber];
                productCount = Integer.parseInt(b);
                sum[productNumber] = prices[productNumber] * productCount;
                count[productNumber] = productCount;
                basket.addToCart(productNumber,productCount);
                basket.saveTxt(saveFile);
            }
            basket.printCart();
            for (int i = 0; i < 3; i++) {
                if (count[i] != 0) {
                    System.out.println(products[i] + " " + count[i] + " шт " + prices[i] + " руб/шт " +
                            sum[i] + " руб в сумме ");

                }
                sumProducts += sum[i];
            }
            System.out.println("Итого " + sumProducts + " руб");

        }



        }