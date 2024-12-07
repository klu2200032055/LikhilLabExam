package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        // Configure Hibernate and build session factory
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        
        // Insert records
        insertClients(sessionFactory);
        
        // Fetch and display all records
        displayAllClients(sessionFactory);
        
        // Close the session factory
        sessionFactory.close();
    }

    private static void insertClients(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Client client1 = new Client();
        client1.setName("Ram");
        client1.setGender("Male");
        client1.setAge(21);
        client1.setLocation("Guntur");
        client1.setEmail("Ram@gmail.com");
        client1.setMobileNumber("8106178456");

        Client client2 = new Client();
        client2.setName("Sita");
        client2.setGender("Female");
        client2.setAge(18);
        client2.setLocation("Vijaywada");
        client2.setEmail("Sita@gmail.com");
        client2.setMobileNumber("81061987456");

        session.save(client1);
        session.save(client2);

        transaction.commit();
        session.close();
        System.out.println("Clients inserted successfully!");
    }

    private static void displayAllClients(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        List<Client> clients = session.createQuery("from Client", Client.class).getResultList();
        
        System.out.println("All Clients:");
        for (Client client : clients) {
            System.out.println(client);
        }
        
        session.close();
    }
}
