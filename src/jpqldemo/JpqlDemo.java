/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpqldemo;

import entity.Student;
import entity.Studypoint;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static org.eclipse.persistence.jpa.rs.util.JPARSLogger.exception;

/**
 *
 * @author Dino
 */
public class JpqlDemo {

    /**
     * @param args the command line arguments
     */
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemoPU");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        // TODO code application logic here

        // Assignment C
        int assignmentnumber = 7; // 1 - 7 for testing ... (0 or 8-* for no testing.)
        // tests assignment C 1-7 choose a assignment number and hit run. (SOUT will show results)

        if (assignmentnumber == 1) {
            Query query = em.createQuery("Select s from Student s");
            List<Student> list = query.getResultList();

            for (Student e : list) {
                System.out.println("Student NAME : " + e.getFirstname() + " " + e.getLastname());
            }

        }

        if (assignmentnumber == 2) {

            Query query = em.createQuery("Select s from Student s WHERE s.firstname LIKE '%Jan%'");
            List<Student> list = query.getResultList();

            for (Student e : list) {
                System.out.println("Student NAME : " + e.getFirstname() + " " + e.getLastname());
            }

        }

        if (assignmentnumber == 3) {
            Query query = em.createQuery("Select s from Student s WHERE s.lastname LIKE '%Olsen%'");
            List<Student> list = query.getResultList();

            for (Student e : list) {
                System.out.println("Student NAME : " + e.getFirstname() + " " + e.getLastname());
            }
        }

        if (assignmentnumber == 4) {
            Query query = em.createQuery("SELECT s FROM Studypoint s, Student st WHERE st = s.studentId and st.id = 1");
            List<Studypoint> list = query.getResultList();

            int stadypointensne = 0;

            for (Studypoint e : list) {
                stadypointensne = e.getScore() + stadypointensne;
            }

            System.out.println("study points for student with the ID 1 (Hardcoded) : " + stadypointensne);
        }

        if (assignmentnumber == 5) {
            int user = 0;
            Query users = em.createQuery("SELECT s from Student s");

            List<Student> studentlist = users.getResultList();

            for (Student student : studentlist) {
                ++user;

                Query query = em.createQuery("SELECT s FROM Studypoint s, Student st WHERE st = s.studentId and st.id = ?1");
                query.setParameter(1, user);
                List<Studypoint> list = query.getResultList();
                int stadypointensne = 0;

                for (Studypoint e : list) {
                    stadypointensne = e.getScore() + stadypointensne;
                }
                System.out.println("study points for student " + student.getFirstname() + " " + student.getLastname() + " : " + stadypointensne);
            }
        }

        if (assignmentnumber == 6) {
            HashMap hmap = new HashMap();
            int user = 0;
            Query users = em.createQuery("SELECT s from Student s");

            List<Student> studentlist = users.getResultList();

            for (Student student : studentlist) {
                ++user;

                Query query = em.createQuery("SELECT s FROM Studypoint s, Student st WHERE st = s.studentId and st.id = ?1");
                query.setParameter(1, user);
                List<Studypoint> list = query.getResultList();
                int stadypointensne = 0;

                for (Studypoint e : list) {

                    stadypointensne = e.getScore() + stadypointensne;
                }
                hmap.put(student.getFirstname() + " " + student.getLastname(), stadypointensne);
                //  System.out.println("study points for student " + student.getFirstname() + " " + student.getLastname() + " : "  + stadypointensne);
            }
            Set set = hmap.entrySet();
            Iterator iterator = set.iterator();
            String name = "";
            int studypoints = 0;
            while (iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry) iterator.next();
                if ((Integer) mentry.getValue() > studypoints) // does not check if two highest has same studypoints.
                {
                    name = (String) mentry.getKey();
                    studypoints = (Integer) mentry.getValue();
                } else {

                }

            }
            System.out.println(name + " Has the highest amount of study points : " + studypoints);
        }

        if (assignmentnumber == 7) {
            HashMap hmap = new HashMap();
            int user = 0;
            Query users = em.createQuery("SELECT s from Student s");

            List<Student> studentlist = users.getResultList();

            for (Student student : studentlist) {
                ++user;

                Query query = em.createQuery("SELECT s FROM Studypoint s, Student st WHERE st = s.studentId and st.id = ?1");
                query.setParameter(1, user);
                List<Studypoint> list = query.getResultList();
                int stadypointensne = 0;

                for (Studypoint e : list) {

                    stadypointensne = e.getScore() + stadypointensne;
                }
                hmap.put(student.getFirstname() + " " + student.getLastname(), stadypointensne);
                //  System.out.println("study points for student " + student.getFirstname() + " " + student.getLastname() + " : "  + stadypointensne);
            }
            Set set = hmap.entrySet();
            Iterator iterator = set.iterator();
            String name = "";
            int studypoints = 50000000; // unrealistic number so it allways runs the if() later on...
            int loweststudypoints = 0;
            while (iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry) iterator.next();
                if ((Integer) mentry.getValue() < studypoints) // does not check if two highest has same studypoints.
                {
                    name = (String) mentry.getKey();
                    studypoints = (Integer) mentry.getValue();
                    loweststudypoints = studypoints;
                } else {
                    studypoints = (Integer) mentry.getValue();
                }

            }
            System.out.println(name + " Has the lowest amount of study points : " + loweststudypoints);
        }

        int assignmentnumberd = 0; // 1 - 2 for testing ... (0 or 3-* for no testing.)

        // Assignment D
        if (assignmentnumberd == 1) {
            createstudent("jens", "hansen");
        }
        if (assignmentnumberd == 2) {
            
            addstudypoints("New semester shitty.", 6, 6, 3);
            // description of assignment, max study points for the assignment,
            // studypoints you give for assignment work, student ID.
        }

    }

    private static void createstudent(String Firstname, String Lastname) {

       
        em.getTransaction().begin();
        Student student = new Student();
        student.setFirstname(Firstname);
        student.setLastname(Lastname);
         try{
        em.persist(student);
        em.getTransaction().commit();
         }
         catch(Exception e)
         {
             System.out.println(e.getStackTrace());
             em.getTransaction().rollback();
         }
    }

    private static void addstudypoints(String desc, int maxstudypoints, int studypointsearned, int studentid) {
            Query query = em.createQuery("Select s from Student s WHERE s.id = ?1");
            query.setParameter(1, studentid);
            Student student = (Student)query.getSingleResult();
            Studypoint studypoint = new Studypoint();
            studypoint.setDescription(desc);
            studypoint.setMaxval(maxstudypoints);
            studypoint.setScore(studypointsearned);
            studypoint.setStudentId(student);
            em.getTransaction().begin();
            em.persist(studypoint);
            em.getTransaction().commit();
    }

}
