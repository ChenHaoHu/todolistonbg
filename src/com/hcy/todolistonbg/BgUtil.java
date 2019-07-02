package com.hcy.todolistonbg;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


/**
 * 主要操作类
 */
public class BgUtil {

    public static boolean handleBg(List<String> list) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("app.propertice"));
            String tmpgPath = properties.getProperty("tmpbgpath");
            String bgDir = properties.getProperty("bgdir");
            BufferedImage read = ImageIO.read(new File(tmpgPath));
            read = writeListOnImage(read, list);
            replaceOldBg(read, bgDir);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static boolean replaceOldBg(BufferedImage image,String bgDir) {

        File dir= new File(bgDir);
        File ne = new File(bgDir+"bg_"+System.currentTimeMillis()+".jpg");

        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return false;
                }
                return true;
            }
        });

        Arrays.stream(files).forEach(file -> {
            file.delete();
        });

        try {
            ImageIO.write(image,"jpg",ne);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static BufferedImage writeListOnImage(BufferedImage image, List<String> list) {

        int width = image.getWidth();
        int height = image.getHeight();
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.RED);
        int size = list.size();
        int stepHeight = (height-500)/(size+1);
        graphics.setFont(new Font(null,Font.CENTER_BASELINE,50));
        graphics.drawString("TODO: ",width/10*7-200,150);
        graphics.setFont(new Font(null,Font.BOLD,40));
        for (int i = 0; i < list.size(); i++) {
            graphics.drawString(list.get(i),width/10*7,200+stepHeight*i);
        }
        return image;
    }
}
