package com.wild.hibernate.demo;

import com.wild.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteMaryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get the student Mary from database
            int theId = 2;
            Student mary = session.get(Student.class, theId);
            System.out.println("\nLoaded student: " + mary);
            System.out.println("Courses: ");
            mary.getCourses().forEach(System.out::println);

            // delete Mary student
            System.out.println("\nDeleting Mary student: " + mary);
            session.delete(mary);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            // add clean up code
            session.close();

            sessionFactory.close();
        }
    }
}
