/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLO;

import com.entities.Block;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Du
 */
public class BlockBLO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("XMLProjectPU");
    
    public boolean add(Block block) {
        EntityManager em = emf.createEntityManager();
        boolean isSuccess = false;
        try {
            em.getTransaction().begin();
            em.persist(block);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            isSuccess = true;
        }
        return isSuccess;
    }
}
