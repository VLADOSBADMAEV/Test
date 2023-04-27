import com.sun.source.tree.BreakTree;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Basket implements Serializable{
    private static final long serialVersionUID = 1L;

    private String[] things;
    private int[] prices;
    private int[] quantities;


    public Basket() {

    }

    public Basket(String[] things, int[] prices) {
        this.things = things;
        this.prices = prices;
        this.quantities = new int[things.length];
    }

    public void addToCart(int productNum, int amount) {
        quantities[productNum] += amount;

    }

    public void printCart() {
        int totalPrice = 0;
        System.out.println("Список покупок:");
        for (int i = 0; i < things.length; i++) {
            if (quantities[i] > 0) {
                int currentPrice = prices[i] * quantities[i];
                totalPrice += currentPrice;
                System.out.printf("%15s%4d р/шт%4d шт%6d p%n", things[i], prices[i], quantities[i], currentPrice);

            }
        }
        System.out.printf("Итого: %dp", totalPrice);

    }

    public void saveTxt(File textFile) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(textFile)) {
          //  for (String good : things) {
          //      out.println(good + "");
         //   }
         //   out.println();

          //  for (var good : prices) {
          //      out.println(good + "");

          //  }
           // out.println();

           // for (int quantity : quantities) {
            //    out.println(quantity + "");

           // }
            out.println(String.join("", things));
            out.println(String.join ("",Arrays.toString(Arrays.stream(prices).mapToObj(String::valueOf).toArray(String []::new))));
            out.println(String.join ("",Arrays.toString(Arrays.stream(quantities).mapToObj(String::valueOf).toArray(String []::new))));


        }

    }

    public static Basket loadFromTxtFile(File textFile) {
        Basket basket = new Basket();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
            String goodsStr = bufferedReader.readLine();
            String pricesStr = bufferedReader.readLine();
            String quantitiesStr = bufferedReader.readLine();

            basket.things = goodsStr.split(" ");
            basket.prices = Arrays.stream(pricesStr.split(""))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
            basket.quantities = Arrays.stream(quantitiesStr.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;

    }

    public void saveBin(File file){
        try(ObjectOutputStream ag = new ObjectOutputStream(new FileOutputStream(file))){
            ag.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Basket loadfromBinfile(File file){
        Basket basket = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            basket = (Basket) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }

}
