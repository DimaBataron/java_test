import java.awt.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/*
   @author: Димончик =)
   Интенсив.
   Классы :
   CoffeeMachine ; DateWork
 */

public class Main {

    public static void main(String[] args) throws AWTException {

        System.out.println("Кофе-машина");
        CoffeeMachine coffeeMachine1 = new CoffeeMachine(new String[]{"Капучино","Экспрессоо","Вода","Молоко"},
                new int[] {150,80,20,50}); // Инициализация новой машины продающей кофе
        coffeeMachine1.outDrink(); // Выводит в консоль наименование напитков которые можно купить
        coffeeMachine1.inputAmount(); // Ввод денег из консоли только рубли без мелочи
        coffeeMachine1.canBuy(); // Вывод что можно купить на введенную сумму

        String dateTimeMS = DateWork.outDate("yyyyMMdd_HHmmss"); // Вывод даты в заданном формате
        System.out.println(DispWork.outResolutionDisp()); // Вывод разрешения монитора

        CoffeeMachine alcoholMachine = new CoffeeMachine(new String[]{"Виски","Брэнди","Водка"},
                new int[]{150,100,80}); // инициализация новой машины продающей спиртное

    }
    /*
    Класс кофе машины CoffeeMachine.
    Конструктор принимает на вход массив строк String наименований напитков drinkNames и
    массив типа int их стоимости соответсвенно.
    Методы:
        - inputAmount()  Ввод из консоли ссумы наличных и проверка корректности данных
        - canBuy()       Выводит в консоль что можно купить на введенную сумму методом inputAmount()
        - outDrink()     выводит на консоль что может продать эта машина
     */

    private static class CoffeeMachine{

        private String[] drinkNames; //Массив наименований напитков
        private int[] drinkPrices; // Массив цен напитков соответсвенно
        private int moneyAmount;  // Деньги потребителя

        //Конструктор инициализирующий переменые класса коффе машины;

        public CoffeeMachine(String[] drinkNames , int[] drinkPrices){
            this.drinkNames = drinkNames;
            this.drinkPrices = drinkPrices;
            this.moneyAmount = 0;
        }

        //Метод ввода из консоли суммы наличных

        public void inputAmount(){
            Scanner incon = new Scanner(System.in);
            int getMoney;
            try {
                System.out.println("Ввод денег");
                getMoney = incon.nextInt();
                if(getMoney>0) this.moneyAmount +=getMoney; // Если введено положительное число
                else {
                    System.out.println("Введено 0 или отрицательное число. Повторите ввод");
                    inputAmount(); // рекурсивный вызов метода ввода денег
                }
            } catch (Exception e) {
                System.out.println("Ошибка : ввод только денег без мелочи");
            }
        }

        /*
        Метод canBuy() Выводит в консоль что можно купить на введенную сумму
         */
        public void canBuy(){
            boolean buy = false;
            if(moneyAmount>0) { // если положили деньги
                for (int i = 0, k = drinkPrices.length; i < k; i++) {
                    if (moneyAmount >= drinkPrices[i]) {
                        System.out.println("Вы можете купить " + drinkNames[i]);
                        buy = true;
                    }
                }
                // если положили деньги но их недостаточно
                if(buy == false){
                    System.out.println("Денег не достаточно положите еще. Депозит :" + moneyAmount + "р.");
                    this.inputAmount();
                    canBuy(); // рекурсивный вызов метода
                }
            }
        }
        /*
        Meтод outDrink() Выводит в консоль то что может продать эта машина
         */
        public void outDrink(){
            System.out.println(" ");
            for(int i = 0, k = drinkPrices.length; i < k; i++)
                    System.out.println(drinkNames[i] + " - " +drinkPrices[i] + " р.");
        }

    }

    /*
    Класс содержащий методы работы с датой DateWork
    Методы :
        - outDate Возвращает строку в заданном формате
     */

    private static class DateWork{

        // Метод возвращает дату в заданном формате

        public static String outDate(String format){
            String dateOut;
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            //Date date = new Date();
            dateOut = dateFormat.format(new Date());
            System.out.println(dateOut);
            return dateOut;
        }
    }

    /*
    Класс DispWork содержащий статические методы работы с монитором DispWork
    Методы :
        - outResolutionDisp() выводит в консоль разрешение монитора
     */

    public static class DispWork{

        // метод выводит в консоль разрешение монитора
        public static String outResolutionDisp() throws AWTException {
            String resolution;
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            resolution = Integer.toString(image.getWidth());
            resolution = resolution.concat(" x " + Integer.toString(image.getHeight()));
            return  resolution;
        }
    }

}
