package com.wild.hibernate.demo;

import com.wild.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        // create a student object
        System.out.println("Create new student object...");
        Student tempStudent = new Student("Daffy", "Duck", "daffy@gmail.com");

        try {
            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            // commit transaction
            session.getTransaction().commit();

            // MY NEW CODE

            // find out the student's id: primary key
            System.out.println("Save student. Generated id: " + tempStudent.getId());

            // now get a new session and start transaction
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: " + tempStudent.getId());

            Student myStudent = session.get(Student.class, tempStudent.getId());

            System.out.println("Get complete: " + myStudent);

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }

    }
}