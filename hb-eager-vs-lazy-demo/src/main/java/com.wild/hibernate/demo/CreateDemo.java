package com.wild.hibernate.demo;

import com.wild.hibernate.demo.entity.Instructor;
import com.wild.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        // create the objects
//        Instructor tempInstructor =
//                new Instructor("Andrey", "Wild", "andrey_wild@gmail.com");
//
//        InstructorDetail tempInstructorDetail =
//                new InstructorDetail("http://www.wild.com/youtube", "baseball");

        Instructor tempInstructor =
                new Instructor("Madhu", "Patel", "madhu@gmail.com");

        InstructorDetail tempInstructorDetail =
                new InstructorDetail("http://www.youtube.com", "guitar");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        try {
            // start a transaction
            session.beginTransaction();

            // save the instructor
            //
            // Note: this will ALSO save the details object
            // because of CascadeType.ALL
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }

    }
}
