/*
    Класс содержит методы для работы с DropBox
    Необходимые бибилиотеки :
        - dropbox-core-sdk-3.1.1.jar
        - jackson-core-2.7.4.jar
    см. https://github.com/DimaBataron/dropbox-sdk-java

    Методы :
        - linkAnAccount(String mod) // возвращает объект класса DbxClientV2 используемого для доступа к
        // DropBox

        - unloadStream(DbxClientV2 client, InputStream is, String name) // метод загрузки потока в DropBox
        // возвращает boolean true если успешно в противном случае false
        client - принимает экземпляр содержащего модификатор доступа к DropBox
        is - поток содержащий массив байт изображения
        name - имя под которым необходимо сохранить файл переданный в is

    @author Батарон Д.А.
 */

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import java.io.IOException;
import java.io.InputStream;

public class DropBoxInter {

    // метод доступа к аккаунту на DropBox

    public static DbxClientV2 linkAnAccount(String mod){
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, mod);
        return client;
    }

    // метод загрузки потока в DropBox

    public static boolean unloadStream(DbxClientV2 client, InputStream is, String name) throws
            IOException, DbxException {
        try {
            client.files().uploadBuilder(name).uploadAndFinish(is);
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }


}
