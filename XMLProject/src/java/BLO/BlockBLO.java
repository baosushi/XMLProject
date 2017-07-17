/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLO;

import com.entities.Block;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Du
 */
public class BlockBLO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("XMLProjectPU");

    public boolean add(Block block) {
        EntityManager em = emf.createEntityManager();
        boolean success = false;
//        if (isDuplicateCode(block.getBlockName())) {
//            return success;
//        }
        try {
            em.getTransaction().begin();
            em.persist(block);
            em.getTransaction().commit();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return success;
    }

    public boolean isDuplicateCode(String blockName) {
        EntityManager em = emf.createEntityManager();
        Block block = null;
        try {
            TypedQuery<Block> query = em.createNamedQuery("Block.findByBlockName", Block.class);
            query.setParameter("blockName", blockName);
            block = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return block != null;
    }

    public Integer getIdByBlockName(String blockName) {
        EntityManager em = emf.createEntityManager();
        Block block = null;
        try {
            TypedQuery<Block> query = em.createNamedQuery("Block.findByBlockName", Block.class);
            query.setParameter("blockName", blockName);
            block = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return block == null ? null : block.getId();
    }
    
    public boolean deleteAllRecord() {
        EntityManager em = emf.createEntityManager();
        boolean success = false;
        try {
            EntityTransaction entr = em.getTransaction();
            entr.begin();
            Query query = em.createQuery("DELETE FROM Block");
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
