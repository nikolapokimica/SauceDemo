package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private static PropertyManager instance = null;
    private static Properties properties = null;
    private static String homePageUrl, username, password, invalidUsername, invalidPassword;

    private PropertyManager() {}

    public static PropertyManager getInstance() {
        if (instance == null) {
            synchronized (PropertyManager.class) {
                instance = new PropertyManager();
                properties = new Properties();
                try {
                    FileInputStream fis = new FileInputStream("src/main/resources/configuration.properties");
                    properties.load(fis);
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                homePageUrl = properties.getProperty("url");
                username = properties.getProperty("username");
                password = properties.getProperty("password");
                invalidUsername = properties.getProperty("invalidUsername");
                invalidPassword = properties.getProperty("invalidPassword");
            }
        }
        return instance;
    }

    public String getHomePageUrl()  {
        return homePageUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getInvalidUsername() {
        return invalidUsername;
    }

    public String getInvalidPassword() {
        return invalidPassword;
    }
}
