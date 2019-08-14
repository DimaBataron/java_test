import java.time.DayOfWeek;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
       /* myCalendar.printCalendar(2019,9,1);
        MyMetod dima = new MyMetod();
        int a = dima.modMetod(); // вызов модифицирующего метода возвращающего int
        System.out.println(a);
        */
    }

    /*1. Измените представленную в этой главе программу вывода календаря таким образом,
 чтобы неделя начиналась с воскресенья. Кроме того, организуйте перевод на новую
  строку в конце предыдущей, но только один раз.
 */
    public static class myCalendar{

        public static void printCalendar(int year, int month,  int day ){
            LocalDate date = LocalDate.of(year,month,day);
            String[] dayofWeek = {"Вос","Пн","Вт","Ср","Чт","Пят","Cуб"};
            // Вывод названий дней недели
            for(int i =0; i<7;i++)
                System.out.printf("%4s",dayofWeek[i]);
            System.out.println();

            //Вывод отступов начала месяца
            int value = date.getDayOfWeek().getValue();
            if(value == 7) value = 0;
            for(int i = 0; i<value; i++){
                System.out.printf("%4s", new String("    "));
            }
            while (date.getMonthValue() == month){
                System.out.printf("%4d", date.getDayOfMonth());
                date = date.plusDays(1);
                value++;
                if(value>6){
                    System.out.println();
                    value = 0;
                }
            }
        }
    }
    /*3. Может ли модифицирующий метод вообще возвращать что-нибудь,
    кроме типа void ? Можно ли создать метод доступа с возвращаемым типом void ,
     т.е. ничего фактически не возвращающий? Приведите по возможности примеры таких методов.

     */
    //Подкласс доступен только в классе Main
    private static class MyMetod{ //закрытый вложенный класс
        private int a;
        public MyMetod(){
            a = 15;
        }
        // пример модифицирующий метод возвращающий int
        public int modMetod(){
            System.out.println("Before:"+ a);
            a = 20;
            return a;
        }
    }




}
