/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLO;

import DTO.UniversityDTO;
import DTO.UniversityListDTO;
import com.entities.University;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Temporary
 */
public class UniversityBLO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("XMLProjectPU");

    public UniversityDTO findById(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<University> query = em.createNamedQuery("University.findById", University.class);
        query.setParameter("id", id);
        University result = query.getSingleResult();

        return result == null ? null : new UniversityDTO(result);
    }
    
    public UniversityDTO findByCode(String code) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<University> query = em.createNamedQuery("University.findByCode", University.class);
        query.setParameter("code", code);
        University result = query.getResultList().get(0);

        return result == null ? null : new UniversityDTO(result);
    }

    public UniversityListDTO getAllUniversityDTO() {
        try {
            UniversityListDTO universityListDTO = new UniversityListDTO();
            List<UniversityDTO> result = new ArrayList<UniversityDTO>();
            EntityManager em = emf.createEntityManager();
            TypedQuery<University> query = em.createNamedQuery("University.findAll", University.class);
            for (University u : query.getResultList()) {
                result.add(new UniversityDTO(u));
            }

            universityListDTO.setUniversity(result);

            return universityListDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
