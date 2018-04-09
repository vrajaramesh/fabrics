package org.sfabrics.orders.components;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDataAccess {

    public static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();
        String[] depts = {"IT", "Account", "Admin", "Sales"};
        //DataFactory df = new DataFactory();
        //for (int i = 1; i <= 50; i++) {
            Employee e = new Employee();
            
            e.setName("rajaramesh");
            e.setAddress("Address");
            e.setDept("10");
            e.setPhone("1232323");
            list.add(e);

            e = new Employee();
            
            e.setName("thulasi");
            e.setAddress("Address");
            e.setDept("101");
            e.setPhone("3232323");
            list.add(e);
            
            e = new Employee();
            
            e.setName("bhavya sri");
            e.setAddress("Address");
            e.setDept("102");
            e.setPhone("3232323");
            list.add(e);
            
e = new Employee();
            
            e.setName("bhavya sri-1");
            e.setAddress("Address");
            e.setDept("103");
            e.setPhone("3232323");
            list.add(e);
       // }
        return list;
    }
}