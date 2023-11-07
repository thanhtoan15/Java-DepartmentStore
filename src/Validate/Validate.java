/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Validate;

import DAO.NhanVienDAO1;
import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author FPTSHOP
 */
public class Validate {
    public static boolean isEmpty(JTextField txtFiled, StringBuilder str, String msg) {
        String strFiled = txtFiled.getText().trim();
        if (strFiled.equals("")) {             
            str.append(msg).append("\n");
//            JOptionPane.showMessageDialog(null, msg);
            txtFiled.setBackground(new Color(250, 250, 210));
            return true;
        }
        txtFiled.setBackground(Color.white);
        return false;
    }
    
    public static boolean isEmail(JTextField txtFiled, StringBuilder str, String msg) {
        String strFiled = txtFiled.getText().trim();
//        String valiemail = "[A-Za-z0-9]+@[a-zA-Z0-9]+(\\\\.[a-zA-Z0-9]+)";
        String valiemail ="^[a-zA-Z]\\w{2,}@\\w{2,}(.\\w{2,}){2,7}$";
        if (!strFiled.matches(valiemail)) {             
            str.append(msg).append("\n");
//            JOptionPane.showMessageDialog(null, msg);
            txtFiled.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtFiled.setBackground(Color.white);
        return true;
    }
//    public static boolean isEmailDuplucation(JTextField txtFiled, StringBuilder str, String msg) {
//        String strFiled = txtFiled.getText().trim();
////        String valiemail = "[A-Za-z0-9]+@[a-zA-Z0-9]+(\\\\.[a-zA-Z0-9]+)";
////        String valiemail ="^[a-zA-Z]\\w{2,}@\\w{2,}(.\\w{2,}){2,7}$";
//NhanVienDAO1 dao = new NhanVienDAO1();
//        if (dao.findByEmail(txtFiled.getText()).equals(txtFiled.getText())) {             
//            str.append(msg).append("\n");
////            JOptionPane.showMessageDialog(null, msg);
//            txtFiled.setBackground(new Color(250, 250, 210));
//            return false;
//        }else{
////            return true;
//        }
//        txtFiled.setBackground(Color.white);
//        return true;
//    }
      public static boolean isEmail1(JTextField txtFiled, StringBuilder str, String msg) {
        String strFiled = txtFiled.getText().trim();
//        String valiemail = "[A-Za-z0-9]+@[a-zA-Z0-9]+(\\\\.[a-zA-Z0-9]+)";
        String valiemail1 ="^[a-zA-Z]\\w{2,}@\\w{2,}(.\\w{2,}){2,7}$";
        if (!strFiled.matches(valiemail1)) {             
            str.append(msg).append("\n");
//            JOptionPane.showMessageDialog(null, msg);
            txtFiled.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtFiled.setBackground(Color.white);
        return true;
    }
      
        public static boolean isDiaChi(JTextField txtFiled, StringBuilder str, String msg) {
        String strFiled = txtFiled.getText().trim();
//        String valiemail = "[A-Za-z0-9]+@[a-zA-Z0-9]+(\\\\.[a-zA-Z0-9]+)";
        String valiemail1 ="^[0-9a-zA-Z\\s,-]+$";
        if (!strFiled.matches(valiemail1)) {             
            str.append(msg).append("\n");
//            JOptionPane.showMessageDialog(null, msg);
            txtFiled.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtFiled.setBackground(Color.white);
        return true;
    }
    
    public static boolean isMH(JTextField txtField, StringBuilder str, String msg){
        String strFiled = txtField.getText().trim();
        String valiMH ="[aA-zZ]{2}[0-9]{1,}";
        if(!strFiled.matches(valiMH)){
            str.append(msg).append("\n");
            txtField.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtField.setBackground(Color.white);
        return true;
    }
    
    public static boolean isTH(JTextField txtField, StringBuilder str, String msg){
        String strFiled = txtField.getText().trim();
        String valiTH ="^[\\p{L} \\.'\\-]+$";
        if(!strFiled.matches(valiTH)){
            str.append(msg).append("\n");
            txtField.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtField.setBackground(Color.white);
        return true;
    }
    
    public static boolean isSDT(JTextField txtFiled, StringBuilder str, String msg) {
        String strFiled = txtFiled.getText().trim();
//        String valiemail = "[A-Za-z0-9]+@[a-zA-Z0-9]+(\\\\.[a-zA-Z0-9]+)";
        String valiemail ="^\\d{1,}$";
        if (!strFiled.matches(valiemail)) {             
            str.append(msg).append("\n");
//            JOptionPane.showMessageDialog(null, msg);
            txtFiled.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtFiled.setBackground(Color.white);
        return true;
    }
    
    public static boolean isName(JTextField txtFiled, StringBuilder str, String msg) {
        String strFiled = txtFiled.getText().trim();
//        String valiemail = "[A-Za-z0-9]+@[a-zA-Z0-9]+(\\\\.[a-zA-Z0-9]+)";
        String valiemail ="^[\\p{L} \\.'\\-]+$";
        if (!strFiled.matches(valiemail)) {             
            str.append(msg).append("\n");
//            JOptionPane.showMessageDialog(null, msg);
            txtFiled.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtFiled.setBackground(Color.white);
        return true;
    }
    
    public static boolean isID(JTextField txtFiled, StringBuilder str, String msg) {
        String strFiled = txtFiled.getText().trim();
//        String valiemail = "[A-Za-z0-9]+@[a-zA-Z0-9]+(\\\\.[a-zA-Z0-9]+)";
        String valiemail ="[a-zA-Z]{2}[0-9]{1,}";
        if (!strFiled.matches(valiemail)) {             
            str.append(msg).append("\n");
//            JOptionPane.showMessageDialog(null, msg);
            txtFiled.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtFiled.setBackground(Color.white);
        return true;
    }
    
    public static boolean isNCC(JTextField txtFiled, StringBuilder str, String msg) {
        String strFiled = txtFiled.getText().trim();
//        String valiemail = "[A-Za-z0-9]+@[a-zA-Z0-9]+(\\\\.[a-zA-Z0-9]+)";
        String valiemail ="[aA-zZ]{3}[0-9]{1,}";
        if (!strFiled.matches(valiemail)) {             
            str.append(msg).append("\n");
//            JOptionPane.showMessageDialog(null, msg);
            txtFiled.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtFiled.setBackground(Color.white);
        return true;
    }
    

}
