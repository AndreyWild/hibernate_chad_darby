package com.wild.hibernate.demo;

import com.wild.hibernate.demo.entity.Course;
import com.wild.hibernate.demo.entity.Instructor;
import com.wild.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get the instructor from db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);
            
            System.out.println("wild: Instructor: " + tempInstructor);

            System.out.println("wild: Courses: " + tempInstructor.getCourses());

            // commit transaction
            session.getTransaction().commit();

            // close the session
            session.close();

            System.out.println("\nwild: The session is now closed!\n");

            // option 1: call getter method while session is open

            // get courses for the instructor
            System.out.println("wild: Courses: " + tempInstructor.getCourses());

            System.out.println("wild: Done!");
        } finally {
            // add clean up code
            session.close();

            sessionFactory.close();
        }

    }
}
