package dima.invoice;

import java.util.ArrayList;

/*
    15. Реализуйте полностью класс Invoice, представленный в разделе 2.6.1.
     Предоставьте метод, выводящий счет-фактуру, и демонстрационную версию программы,
     составляющей и выводящей образец счета-фактуры.

 */
public class Invoice {
    public static class Item {
        private String description;
        private int quantity;
        private double unitPrice;

        public Item(String description, int quantity, double unitPrice) {
            this.description = description;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public double price() {
            return quantity * unitPrice;
        }

        public void priceList(){
            System.out.println(description + " " + quantity + " " + unitPrice);
        }
    }
    private ArrayList<Item> items = new ArrayList<>();
    // Добавление наименования в список
    public void add(Item item){items.add(item); }

    // Метод возвращаюший счет
    public double offenPrice(){
        int len = items.size();
        double offenP=0;
        for(int i =0 ; i<len; i++){
            offenP += items.get(i).price();
            items.get(i).priceList();
        }
        return offenP;
        }

}
