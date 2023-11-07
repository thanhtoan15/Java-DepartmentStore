/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.swing;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import com.raven.swing.statusType;

/**
 *
 * @author FPTSHOP
 */
public class TableStatus extends JLabel {

    public TableStatus() {
        setForeground(Color.WHITE);
    }

    public statusType getType() {
        return type;
    }

    private statusType type;

    public void setType(statusType type) {
        this.type = type;
        setText(type.toString());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (type != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint g3;
            if (type == statusType.PENDING) {
                g3 = new GradientPaint(0, 0, new Color(186, 123, 247), 0, getHeight(), new Color(197, 94, 236));
            } else if (type == statusType.APPROVED) {   
                g3 = new GradientPaint(0, 0, new Color(142, 142, 250), 0, getHeight(), new Color(123, 123, 245));
            } else {
                g3 = new GradientPaint(0, 0, new Color(241, 208, 62), 0, getHeight(), new Color(211, 184, 61));
            }
            g2.setPaint(g3);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 1, 1);
        }

    }

}
