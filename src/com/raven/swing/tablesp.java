/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.raven.swing.statusType;

/**
 *
 * @author FPTSHOP
 */
public class tablesp extends JTable{
    public tablesp(){
        setFont(new java.awt.Font("Tahoma", 0, 14));
        setShowHorizontalLines(true);
        setGridColor(new Color(230,230,230));
        setRowHeight(35);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int i, int i1) {
                tableheader header = new tableheader(o+"");
                if(i1 == 7){
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }    
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object o, boolean selected, boolean hasFocus, int i, int i1) {
               if(i1 !=8){
                   Component com =  super.getTableCellRendererComponent(table, o, selected, hasFocus, i, i1);
                   com.setBackground(Color.WHITE);
                   setBorder(noFocusBorder);
                   if(selected){
                       com.setFont(new java.awt.Font("Tahoma", 0, 15));
                       com.setBackground(new Color(230,230,230));
                       com.setForeground(new Color(0,0,0));
                   }else{
                       com.setForeground(new Color(80, 80, 80));
                   }
                   return com;
               }else{
                   statusType type = (statusType)o;
                   CellStatus cell = new CellStatus(type);
                   return cell;
               } 
               
            }
            
        });
    }
    
    public void addRow(Object[] row){
        DefaultTableModel model = (DefaultTableModel)getModel();
        model.addRow(row);
    }
}
