package com.wild.hibernate.demo;

import com.wild.hibernate.demo.entity.Course;
import com.wild.hibernate.demo.entity.Instructor;
import com.wild.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

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

            // option 2: Hibernate query with HQL

            // get the instructor from db
            int theId = 1;

            // Hibernate query with HQL
            Query<Instructor> query =
                    session.createQuery("SELECT i FROM Instructor i "
                    + "JOIN FETCH i.courses WHERE i.id=:theInstructorId",
                    Instructor.class);

            // set parameter on query
            query.setParameter("theInstructorId", theId);

            // execute query and get instructor?
            Instructor tempInstructor = query.getSingleResult();
            
            System.out.println("wild: Instructor: " + tempInstructor);

            // commit transaction
            session.getTransaction().commit();

            // close the session
            session.close();

            System.out.println("\nwild: The session is now closed!\n");

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
