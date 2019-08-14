import java.time.DayOfWeek;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        myCalendar.printCalendar(2019,9,1);
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
}
