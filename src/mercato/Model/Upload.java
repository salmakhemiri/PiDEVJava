/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.Model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yassine
 */
public class Upload {
    
    private Properties properties;
    
    public String upload(File file,String dossier) throws FileNotFoundException, IOException {
        BufferedOutputStream stream = null;
        String globalPath = "src/mercato/GUI/photos";
        String localPath = "src/mercato/GUI/photos";
        
//        String globalPath="/Applications/MAMP/htdocs/MTOCrowdRise/web/uploads/" + dossier;
//        String localPath="localhost:8888/MTOCrowdRise/";

        
        
        String fileName = file.getName();
        fileName=fileName.replace(" ", "_");
        try {
            Path p = file.toPath();
            
            byte[] bytes = Files.readAllBytes(p);
    
            File dir = new File(globalPath);
            System.out.println("test if it work");
            if (!dir.exists())
                dir.mkdirs();
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath()+File.separator + fileName);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return localPath+"/"+fileName;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        } catch (IOException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
}