package org.sfabrics.orders.components;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JComboBoxFilterMain {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeDataAccess.getEmployees();
        JComboBox<Employee> comboBox = new JComboBox<>(
                employees.toArray(new Employee[employees.size()]));

        ComboBoxFilterDecorator<Employee> decorate = ComboBoxFilterDecorator.decorate(comboBox,
                CustomComboRenderer::getEmployeeDisplayText,
                JComboBoxFilterMain::employeeFilter);

        comboBox.setRenderer(new CustomComboRenderer(decorate.getFilterTextSupplier()));

        JPanel panel = new JPanel();
        panel.add(comboBox);

        JFrame frame = createFrame();
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static boolean employeeFilter(Employee emp, String textToFilter) {
        if (textToFilter.isEmpty()) {
            return true;
        }
        return CustomComboRenderer.getEmployeeDisplayText(emp).toLowerCase()
                                  .contains(textToFilter.toLowerCase());
    }

    private static JFrame createFrame() {
        JFrame frame = new JFrame("JComboBox Filter Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 300));
        return frame;
    }
}