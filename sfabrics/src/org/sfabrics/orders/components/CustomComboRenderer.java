package org.sfabrics.orders.components;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class CustomComboRenderer extends DefaultListCellRenderer {
    public static final Color background = new Color(225, 240, 255);
    private static final Color defaultBackground = (Color) UIManager.get("List.background");
    private static final Color defaultForeground = (Color) UIManager.get("List.foreground");
    private Supplier<String> highlightTextSupplier;

    public CustomComboRenderer(Supplier<String> highlightTextSupplier) {
        this.highlightTextSupplier = highlightTextSupplier;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) 
    {
    	System.out.println("value:"+value);
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Employee emp = (Employee) value;
        if (emp == null) {
            return this;
        }
        String text = getEmployeeDisplayText(emp);
        System.out.println("text:"+text);
        text = HtmlHighlighter.highlightText(text, highlightTextSupplier.get());
        //System.out.println("getListCellRendererComponent:"+text);
        this.setText(text);
        System.out.println(this.getText());
        //System.out.println(this.gets);
        if (!isSelected) {
            this.setBackground(index % 2 == 0 ? background : defaultBackground);
        }
        this.setForeground(defaultForeground);
        return this;
    }

    public static String getEmployeeDisplayText(Employee emp) {
        if (emp == null) {
            return "";
        }
        return String.format("%s [%s]", emp.getName(), emp.getDept());
    }
}