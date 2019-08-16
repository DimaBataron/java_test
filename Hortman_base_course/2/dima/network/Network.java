package dima.network;

import java.util.ArrayList;

/*
14. Скомпилируйте класс Network, представленный в этой главе. Обратите внимание на то,
что файл внутреннего класса называется Network$Member.class. Воспользуйтесь утилитой javap ,
 чтобы исследовать сгенерированный код. Так, по следующей команде:
javap -private имяКласса выводятся методы и переменные экземпляра. Выявите среди выводимых
 результатов ссылку на объемлющий класс. (В Linux и Mac OS X перед знаком $ в имени класса
 следует указать знак \ при выполнении утилиты javap .)
 */
public class Network {
    private ArrayList<Member> members;

    public Network(){
    members = new ArrayList<>();
    }
    public class Member{
        private String name;
        private ArrayList<Member> friends;

        public Member(String name){
            this.name = name;
            friends = new ArrayList<>();
        }
        //Метод удаления записи
        public void leave(){
            members.remove(this);
        }

    }
    // Метод для ввода члена в социальную сеть
    public Member enroll(String name){
        Member newMember = new Member(name);
        members.add(newMember);
        return newMember;
    }
}
