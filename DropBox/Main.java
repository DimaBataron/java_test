/*
        Программа создает поток отправки изображений экрана в DropBox
 */

public class Main {

    public static void main(String[] args) {

        String modName = "G4i8D1P2ysAAAAAAAAAACiBYwf0dpCq3Gb3n0MvhUqxkvkSCnm5Cttn79fBw5jaL"; // модификатор доступа
        MyThread thread1 = new MyThread(1, modName);

        thread1.start();

    }
}



