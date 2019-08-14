import java.time.DayOfWeek;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
       /* myCalendar.printCalendar(2019,9,1); // вывод календаря
        MyMetod dima = new MyMetod();
        int a = dima.modMetod(); // вызов модифицирующего метода возвращающего int
        System.out.println(a);
        */
       Point coordinates = new Point(3, 4).translate(1, 3).scale((float) 0.5);
       System.out.println(coordinates.getX() + " " + coordinates.getY());

       // тот-же результат используя модифицирующий метод
       ModPoint modCoordinates = new ModPoint(3,4);
       modCoordinates.translate(1,3);
       modCoordinates.scale(0.5f);
       System.out.println(modCoordinates.getPx() + " " + modCoordinates.pY);

       Car lada = new Car(0.1f); // литр на 10 км
        lada.gasStation(10f); // заправили
        lada.translate(10f,0); // проехали 10км
        System.out.println(lada.getFuel()+ " " + lada.getWay()); // 9 литров 10км
        lada.translate(-1f,-1f); // вернулись обратно на на  sqrt(1+1) - больше
        System.out.println(lada.getFuel()+ " " + lada.getWay());

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

    /*5. Реализуйте неизменяемый класс P o in t, описывающий точку на плоскости.
    Предоставьте его конструктор, чтобы задать конкретную точку; конструктор без аргументов,
    чтобы задать точку в начале координат; а также методы g e tX (), g e tY (),
    t r a n s la t e () и s c a le ( ) . В частности, метод t r a n s la t e ()
    должен перемещать точку на определенное расстояние в направлении координат х и у,
     а метод s c a le () — изменять масштаб по обеим координатам на заданный коэффициент.
      Реализуйте эти методы таким образом, чтобы они возвращали новые точки в качестве результата.
       Например, в следующей строке кода:
    Point р = new Point(3, 4).translate(1, 3).scale(0.5);
    в переменной р должна быть установлена точка с координатами (2, 3,5).
    (Тут нет модифицирующих методов. Все методы возвращают новый экземпляр класса типа Point)
     */

    /**
     * содержит методы возвращающие объекты типа <code>класса Point</code> хранящие
     * положение точки на плоскости х у
     * @author Батарон Д.А.
     * @version 0.1
     */
    private static class Point{
        private float pX;
        private float pY;

        /**
         * конструктор устанавливает координаты точек в объекте
         * @param pX указывает на координаты которые необходимо установить по х
         * @param pY указывает на координаты которые необходимо установить по н
         */
        public Point(float pX, float pY){
            this.pX = pX;
            this.pY = pY;
        }
        public Point(){
            this(0,0);
        }

        public float getX(){
            return pX;
        }

        public float getY(){
            return pY;
        }

        /**
         * метод перемещения по координатным осям
         * @param tX расстояние перемещения по оси х
         * @param tY расстояние перемещения п оси у
         * @return возвращает новый обьект типа <code>Point</code> c измененными координатами указанным в параметрах
         */
        public Point translate(float tX,float tY){
            tX += pX;
            tY += pY;
            return new Point(tX,tY);
        }

        /**
         * метод маштабирования координат по осям х у
         * @param scl коэффициэнт маштаба
         * @return @return возвращает новый обьект типа <code>Point</code> c измененными координатами коф scl
         */
        public Point scale(float scl){
            float sX = pX*scl;
            float sY = pY*scl;
            return new Point(sX,sY);
        }
    }
    /*
    6.Повторите предыдущее упражнение, но на этот раз сделайте методы
     t r a n s la t e () и s c a le () модифицирующими.
     */
    public static class ModPoint{
        private float pX;
        private float pY;
        private float scl;
        public ModPoint(float pX,float pY){
            this.pX= pX;
            this.pY = pY;
            scl = 1;
        }
        public ModPoint(){
            this(0,0);
        }

        public float getPx(){
            return pX;
        }
        public float getPy(){
            return pY;
        }

        // Модифицирующий метод перемещения
        public void translate(float tX,float tY){
            pX += tX;
            pY += tY;
        }
        // Модифицирующий метод маштабирования
        public void scale(float scl){
            this.scl = scl;
            pX *= scl;
            pY *= scl;
        }
    }
    /*
    7. Введите документирующие комментарии в обе версии класса Point из предыдущих упражнений.
     */
    /*
    9. Реализуйте класс Саг, моделирующий передвижение автомобиля на бензиновом топливе по оси х.
     Предоставьте методы для передвижения автомобиля на заданное количество километров,
     заполнения топливного бака заданным количеством литров бензина, вычисления расстояния,
     пройденного от начала координат, а также уровня топлива в баке. Укажите расход топлива
      (в км/л) в качестве параметра конструктора данного класса. Должен ли этот класс быть
       неизменяемым и почему?
     */
    public static class Car{
        private float fuel; // топливо
        private Point kord; // текущая точка
        private float way; // пройденный путь
        private float consumption; // расход

        public Car(float consumption){
            this.consumption = consumption;
            fuel = 0;
            kord= new Point(0,0);
            way = 0;
        }
        public void gasStation(float fuel){ //Заправим
            this.fuel += fuel;
        }
        public void translate(float x , float y){
            float tX = x-kord.getX();
            float tY = y-kord.getY();
            kord = kord.translate(tX,tY); // запоминаем последние координаты
            float bias = (float)Math.sqrt(tX*tX + tY*tY); // смещение
            way += bias;
            fuel -= bias * consumption; // запоминаем соженное топливо
        }
        public float getFuel(){
            return fuel;
        }
        public float getWay(){
            return way;
        }

    }

}
