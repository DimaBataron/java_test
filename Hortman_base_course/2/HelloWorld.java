/*
12. Создайте исходный файл HelloWorld.java, где класс HelloWorld
объявляется в пакете chOl.secOl. Разметите его в каком-нибудь каталоге,
 но только не в подкаталоге chOl/secOl. Выполните из этого
 каталога команду javac HelloWorld.java . Получите ли вы в итоге файл класса и где именно?
  Затем выполните команду java HelloWorld. Что при этом произойдет и почему?
  (Подсказка: выполните команду javap HelloWorld и проанализируйте полученное
  предупреждающее сообщение.) И наконец, попробуйте выполнить команду javac -d . HelloWorld.java.
   Почему такой способ лучше?
 */
package ch01.sec01;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.print("HelloClass");
    }
}
/*
    1.После выполнения javac HelloWorld.java в этом каталоге создается файл javac HelloWorld.class

    2.E:\A\ProjJava>javap HelloWorld
    Warning: Binary file HelloWorld contains ch01.sec01.HelloWorld

    3. E:\A\ProjJava>java HelloWorld
    Error: Could not find or load main class HelloWorld
    Caused by: java.lang.NoClassDefFoundError: ch01/sec01/HelloWorld (wrong name: HelloWorld)

    4. E:\A\ProjJava>javac -d . HelloWorld.java Создается файл HelloWorld.class в указанном каталоге
    в package
    5. java ch01.sec01.HelloWorld для выполнения из каталога ch01/sec01.HelloWorld


 */