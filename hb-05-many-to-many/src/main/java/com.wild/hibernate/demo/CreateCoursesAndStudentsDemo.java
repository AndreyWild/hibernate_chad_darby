package com.wild.hibernate.demo;

import com.wild.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndStudentsDemo {

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

            // create a course
            Course tempCourse = new Course("Pacman - How To Score One Million Points");

            // save the course
            System.out.println("\nSaving the course ...");
            session.save(tempCourse);
            System.out.println("Saved the course: " + tempCourse);

            // create the students
            Student tempStudent1 = new Student("Jhon", "Doe", "jhon@gmail.com");
            Student tempStudent2 = new Student("Mary", "Public", "mary@gmail.com");

            // add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            // save the students
            System.out.println("\nSaving students ...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved students: " + tempCourse.getStudents());

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
