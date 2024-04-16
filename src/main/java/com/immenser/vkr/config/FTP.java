package com.immenser.vkr.config;
import org.apache.commons.net.ftp.FTPClient;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Properties;


public class FTP {

        private static final String host;
        private static final String username;
        private static final String password;
        private static final int port;

        static {
                Properties properties = new Properties();
                try {
                        properties.load(Database.class.getClassLoader().getResourceAsStream("ftp.properties"));
                } catch (IOException e) {
                        e.printStackTrace();
                }

                host = properties.getProperty("host");
                username = properties.getProperty("username");
                password = properties.getProperty("password");
                port = Integer.parseInt (properties.getProperty("port"));
        }

        public static BufferedImage ftpConn(String photo) {
                FTPClient fClient = new FTPClient();
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                BufferedImage image = null;
                try {
                        fClient.connect(host, port);
                        fClient.enterLocalPassiveMode();
                        fClient.login(username, password);
                        boolean can = fClient.retrieveFile(photo, output);
                        System.out.println(can);
                        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
                        image = ImageIO.read(input);
                        output.flush();
                        output.close();
                        fClient.logout();
                        fClient.disconnect();
                } catch (IOException ex) {
                        System.err.println(ex);
                }
                return image;
        }
}