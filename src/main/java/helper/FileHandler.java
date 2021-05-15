package helper;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.dao.xml.XmlAppDao;
import util.Constants;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * @author Milinda
 */
public class FileHandler {

    public static Properties propUser;
    public static Properties propSys;

    public static void loadProperties() {
        propUser = FileHandler.loadPropertyFile("user.properties");
        propSys = FileHandler.loadPropertyFile(".data/system.properties");
    }

    public static void writeCoibnDataToXML(String file, XmlAppDao pojo, String coinname) {
        XmlMapper xmlMapper = new XmlMapper();
        File writeFile = null;
        try {
            writeFile = new PropertiesLoader().getFileFromResource(file);
        } catch (IllegalArgumentException e) {
            ClassLoader classLoader = new FileHandler().getClass().getClassLoader();
            // Getting resource(File) from class loader
            File parentDir = new File(classLoader.getResource(Constants.XML_DEAL_RECORDS_DIR).getFile());
            File newFile = new File(parentDir + File.separator + coinname + Constants.XML_FILE);
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

    public static Object readXMLFile(String file, Class<?> pojo) {
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

    public static Properties loadPropertyFile(String filename) {
        Properties prop = null;

        try {
            InputStream input = FileHandler.class.getClassLoader().getResourceAsStream(filename);
            prop = new Properties();
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
