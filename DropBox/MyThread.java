import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {
    private int threadNumber;
    private String modName;
    private DbxClientV2 myAk =null; // структура доступа к DropBox
    private InputStream is = null; // поток байтов изображения экрана в фомате png

    public MyThread(int threadNumber,String modName){
        this.threadNumber = threadNumber;
        this.modName = modName;
        myAk = DropBoxInter.linkAnAccount(modName); // инициализируем аккаунт доступа к DropBox
    }
    @Override
    public void run() {

        while (true) {

            // Получение потока байтов в формате png;
            try {
                is = DispWork.outInStrDist("png");
            } catch (AWTException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // получение текущих даты и времени
            String date = "/";
            date = date.concat(outDate("yyyyMMdd_HHmmss") + ".png"); // / + строка даты и времени + .png

            // выгрузка изображения в DropBox
            try {
                DropBoxInter.unloadStream(myAk, is, date);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DbxException e) {
                e.printStackTrace();
            }
            // Ожидание 5 сек
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static String outDate(String format){
        String dateOut;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        //Date date = new Date();
        dateOut = dateFormat.format(new Date());
        return dateOut;
    }
}
