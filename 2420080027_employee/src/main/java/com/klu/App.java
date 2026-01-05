package com.klu;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Employee e1 = new Employee(1, "Ravi", 45000, "Developer");
        Employee e2 = new Employee(2, "Kiran", 60000, "Manager");
        Employee e3 = new Employee(3, "Anu", 75000, "Team Lead");

        session.save(e1);
        session.save(e2);
        session.save(e3);

        tx.commit();

        System.out.println("\nEmployees with Salary > 50000:");
        List<Employee> highSalary = session
                .createQuery("FROM Employee e WHERE e.salary > 50000", Employee.class)
                .list();
        highSalary.forEach(emp ->
                System.out.println(emp.getEmpName() + " - " + emp.getSalary()));

        System.out.println("\nAverage Salary:");
        Double avgSalary = (Double) session
                .createQuery("SELECT AVG(e.salary) FROM Employee e")
                .uniqueResult();
        System.out.println(avgSalary);

        session.close();
    }
}