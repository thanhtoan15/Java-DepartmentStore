/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Z18KAI
 */
public class Set_icon_frame {
    public static Image getIconFrame () {
        URL url = Set_icon_frame.class.getResource("/Image/rubber-plant.png");
        return new ImageIcon(url).getImage();
    }
}
