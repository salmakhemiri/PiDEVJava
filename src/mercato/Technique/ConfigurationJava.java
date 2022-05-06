/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.Technique;

/**
 *
 * @author PC-yassine
 */
public class ConfigurationJava {

    private static String OS = System.getProperty("os.name").toLowerCase();
    public static String profilsPath = "http://localhost/photos/Mercato";
    public static String productsPath = "http://localhost/photos/Mercato";
    public static String localPath = "http://localhost";


    public String getProfilsPath() {
        return profilsPath;
    }

     public String getOS() {
     return OS;
    }
}
