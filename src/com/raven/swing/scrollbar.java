/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.swing;

import combo_suggestion.ModernScrollBarUI;
import javax.swing.JScrollBar;
import java.awt.Color;
import java.awt.Dimension;


/**
 *
 * @author FPTSHOP
 */
public class scrollbar extends JScrollBar{
     public scrollbar() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
        setBackground(new Color(242, 242, 242));
        setUnitIncrement(20);
    }
    
}
