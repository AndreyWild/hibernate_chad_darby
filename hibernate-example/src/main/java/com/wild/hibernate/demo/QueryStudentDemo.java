package com.wild.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.wild.hibernate.demo.entity.Student;

import java.util.List;


public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> students = session.createQuery("from Student").list();

            // display the students
            System.out.println("All students:");
            displayStudents(students);

            // query students: lastName='Doe'
            students = session.createQuery("FROM Student s WHERE s.lastName='Doe'").list(); // use the java property name (not column name)

            // display the students
            System.out.println("\n\nStudents who have last name of \"Doe\":");
            displayStudents(students);

            // query students: lastName='Doe' OR firstName='Daffy'
            students = session.createQuery("FROM Student s WHERE s.lastName='Doe' OR s.firstName='Daffy'").list();

            // display the students
            System.out.println("\n\nStudents who have last name of \"Doe\" OR first name of \"Daffy\":");
            displayStudents(students);

            // query students where email LIKE '%gmail.com'
            students = session.createQuery("FROM Student s WHERE s.email LIKE '%gmail.com'").list();

            // display the students
            System.out.println("\n\nStudents whose email ends with '%gmail.com':");
            displayStudents(students);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }


    }

    private static void displayStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
