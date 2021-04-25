package operation;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.dao.app.XmlAppDao;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Milinda
 */
public class FileHandler {

    public static void writeXMLFile(String file, XmlAppDao pojo){
        XmlMapper xmlMapper = new XmlMapper();
        File writeFile = null;
        try {
            writeFile = new PropertiesLoader().getFileFromResource(file);
        } catch (URISyntaxException e) {
            System.err.println("file not found! ");
        }

        try {
            xmlMapper.writeValue(writeFile, pojo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readXMLFile(String file, Class<?> pojo){
        XmlMapper xmlMapper = new XmlMapper();
        File readFile = null;
        try {
            readFile = new PropertiesLoader().getFileFromResource(file);
        } catch (URISyntaxException e) {
            System.err.println("file not found! ");
        }

        try {
            return xmlMapper.readValue(readFile, pojo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
