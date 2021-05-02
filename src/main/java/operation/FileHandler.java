package operation;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.dao.app.XmlAppDao;
import util.Constants;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Milinda
 */
public class FileHandler {

    public static void writeCoibnDataToXML(String file, XmlAppDao pojo, String coinname){
        XmlMapper xmlMapper = new XmlMapper();
        File writeFile = null;
        try {
            writeFile = new PropertiesLoader().getFileFromResource(file);
        } catch (IllegalArgumentException  e) {
            ClassLoader classLoader = new FileHandler().getClass().getClassLoader();
            // Getting resource(File) from class loader
            File parentDir=new File(classLoader.getResource(Constants.XML_DEAL_RECORDS).getFile());
            File newFile = new File(parentDir+File.separator + coinname+Constants.XML_FILE);
            try {
                xmlMapper.writeValue(newFile, pojo);
                return;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
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
