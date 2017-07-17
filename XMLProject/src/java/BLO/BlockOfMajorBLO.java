/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLO;

import com.entities.BlockOfMajor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Du
 */
public class BlockOfMajorBLO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("XMLProjectPU");

    public boolean add(BlockOfMajor blockOfMajor) {
        EntityManager em = emf.createEntityManager();
        boolean success = false;
        try {
            em.getTransaction().begin();
            em.persist(blockOfMajor);
            em.getTransaction().commit();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return success;
    }
    
    public boolean deleteAllRecord() {
        EntityManager em = emf.createEntityManager();
        boolean success = false;
        try {
            EntityTransaction entr = em.getTransaction();
            entr.begin();
            Query query = em.createQuery("DELETE FROM BlockOfMajor");
            query.executeUpdate();
            entr.commit();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return success;
    }
}
