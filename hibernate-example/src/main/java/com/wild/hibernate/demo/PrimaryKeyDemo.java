package com.wild.hibernate.demo;

import com.wild.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        // create a student object
        System.out.println("Create 3 student objects...");
        Student tempStudent1 = new Student("Paul", "Wall", "paul@gmail.com");
        Student tempStudent2 = new Student("John", "Doe", "john@gmail.com");
        Student tempStudent3 = new Student("Mary", "Public", "mary@gmail.com");

        try {
            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }

    }
}
