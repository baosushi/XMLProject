/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLO;

import com.entities.Major;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Du
 */
public class MajorBLO {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("XMLProjectPU");
    
    public Integer add(Major major) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(major);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return major.getId();
    }
    
    public Integer getIdByCode(String majorCode, Integer universityId) {
        Integer index = null;
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery(""
                    + "SELECT m.id "
                    + "FROM Major m "
                    + "ORDER BY m.id DESC" 
                    + "LIMIT 1");
            index = (Integer) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return index == null ? null : index;
    }
    
    public boolean deleteAllRecord() {
        EntityManager em = emf.createEntityManager();
        boolean success = false;
        try {
            EntityTransaction entr = em.getTransaction();
            entr.begin();
            Query query = em.createQuery("DELETE FROM Major");
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
