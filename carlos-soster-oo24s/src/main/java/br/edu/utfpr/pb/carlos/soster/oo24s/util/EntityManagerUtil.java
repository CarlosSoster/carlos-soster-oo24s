package br.edu.utfpr.pb.carlos.soster.oo24s.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

    private static EntityManagerFactory emf;
    
    public static EntityManager getEntityManager(){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("TrabalhoFinal-CarlosSoster");
        }
        return emf.createEntityManager();
    }
}
