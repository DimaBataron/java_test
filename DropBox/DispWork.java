import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

   /*
    Класс DispWork содержащий статические методы работы с монитором DispWork
    Методы :
        - outResolutionDisp() возвращает строку содержащую разрешение экрана

        - outBufferDisp()  возвращает экземпляр класса BufferedImage содержащий скрин экрана

        - outInStrDist(String expan)  возвращает экземпляр класса InputStream содержащего изображение экрана
         expan указывает на расширение фала
     @author Батарон Д.А.

     */

public class  DispWork{

    // метод выводит в консоль разрешение монитора

    public static String outResolutionDisp() throws AWTException {
        String resolution;
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        resolution = Integer.toString(image.getWidth());
        resolution = resolution.concat(" x " + Integer.toString(image.getHeight()));
        image = null;
        return  resolution;
    }

    // возвращает экземпляр класса BufferedImage содержащий скрин экрана

    public static BufferedImage outBufferDisp() throws AWTException{
        return new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    }

    // возвращает экземпляр класса InputStream содержащего изображение экрана

    public static InputStream outInStrDist(String expan) throws AWTException, IOException {

        BufferedImage image = outBufferDisp(); // получаем скрин экрана
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, expan, os); // записываем в выходной поток изображение в заданном формате
        InputStream is = new ByteArrayInputStream(os.toByteArray()); // преобразуем изображение
        // в байтовый массив и записываем входной поток
        return is; // вохвращаем экземпляр класса
    }
}
