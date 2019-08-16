package dima.queue;
/*
16. Реализуйте в классе Queue неограниченную очередь символьных строк.
 Предоставьте метод add () для ввода элемента в хвост очереди и метод remove ()
 для удаления элемента из головы очереди. Организуйте хранение элементов в виде связного списка узлов,
  создав вложенный класс Node. Должен ли этот класс быть статическим?
  17. Предоставьте итератор — объект, извлекающий по порядку элементы очереди из предыдущего упражнения.
    Сделайте класс Iterator вложенным и определите в нем методы next () и hasNext ().
    Определите в классе Queue метод iterator (), возвращающий ссылку на объект Queue.Iterator.
    Должен ли внутренний класс Iterator быть статическим?
 */
public class Queue {

    private static class Node{
        public Node after;
        public Node before;
        String memory;
        public Node(String memory){
            after = null;
            before = null;
            this.memory = memory;
        }
        public void setAfter(Node after){
            this.after = after;
        }
        public void setBefore(Node before){
            this.before = before;
        }
        public String getString(){
            return memory;
        }
    }
    public class Interator{
       private Node beg = down;
       public String next(){
           Node ret = beg;
           beg = beg.after;
           return ret.getString();
       }

       public boolean hasNext(){
           if(beg!=null) return true;
           else return false;
       }
    }

    public Interator interator(){
        return new Interator();
    }

    private Node down = null;
    private Node top = null;
    private Node run = null;

    public void add(String memory){
        if(down == null){
            down= new Node(memory);
            run = down;
        }
        else{
            top = new Node(memory);
            top.setBefore(run);
            run.setAfter(top);
            run = top;
        }
    }

    public void remove(){
        down = down.after;
        down.before = null;
    }

    public void printString(){
        run = down;
        while (run!=null){
            System.out.print(run.getString() + " ");
            run = run.after;
        }
        run=top;
    }
}
