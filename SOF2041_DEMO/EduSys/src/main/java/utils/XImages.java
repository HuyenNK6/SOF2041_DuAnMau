/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author Huyen
 */
public class XImages {
    public static Image getAppIcon(){
        URL url= XImages.class.getResource("/fpt.png");
        System.out.println(url);
        return new ImageIcon(url).getImage();
    }
//    public static void main(String[] args) {
//        getAppIcon();
//    }
    public static boolean save (File src){
        File dst= new File("src\\main\\resources\\logos",src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();//tạo thư mục
            
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static ImageIcon read(String fileName){
        File path = new File("src\\main\\resources\\logos",fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
