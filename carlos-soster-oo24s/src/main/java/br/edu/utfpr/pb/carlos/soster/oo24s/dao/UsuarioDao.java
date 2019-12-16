package br.edu.utfpr.pb.carlos.soster.oo24s.dao;

import br.edu.utfpr.pb.carlos.soster.oo24s.model.Usuario;
import javax.persistence.Query;

public class UsuarioDao extends GenericDao<Usuario, Long>{
   
    public UsuarioDao() {
        super(Usuario.class);
    }
    
    public Usuario findByEmailAndSenhaNamedQuery(String email, 
            String senha){
        Query query = em.createNamedQuery(
                Usuario.FIND_BY_EMAIL_AND_SENHA);
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        
        return (Usuario) query.getSingleResult();
    }
    
    public Usuario getById(Long id) {
        Query query = em.createQuery("Select u "
                + "FROM Usuario u "
                + "WHERE u.id=:id");
        query.setParameter("id", id);
        return (Usuario) query.getSingleResult();
    }
}




