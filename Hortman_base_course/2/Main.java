 //import dima.network.Network;
//import dima.invoice.Invoice;
import dima.queue.Queue;

public class Main {
    public static void main(String[] args) {
        /*
        Сеть
        Network set = new Network();
        Network.Member dima = set.enroll("Dima");
        dima.leave(); // удаление из списка Network ссылки на экземпляр с именем dima
         */
        /*
        Магаз

        Invoice shop = new Invoice(); // экземпляр покупок
        Invoice.Item unit = new Invoice.Item("Сопли",1,16.50);
        shop.add(unit); // добавляем в список экземпляра сопли
        unit = new Invoice.Item("Херня", 1,39);
        shop.add(unit); // добавляем всякую херню
        double price = shop.offenPrice(); // выводим че там купили и считаем стоимость
        System.out.println("Всего: "+ price);  // выводим общую сумму че там в корзине
          */
        // работа со списком
        Queue list = new Queue(); // создание нового списка
        list.add("Первое");
        list.add("Второе"); // добавление
        list.add("Третее");
        list.add("Четвертое");
        list.printString();  // распечатка из начала очереди
        System.out.println(" ");
        list.remove(); // удаление из вершины списка
        list.printString();
        System.out.println(" ");
        list.add("Пятое");
        list.printString();

        //17
        Queue.Interator interator = list.interator();
        System.out.print("\n");
        while(interator.hasNext()){
            System.out.println(interator.next());
        }
    }
}
