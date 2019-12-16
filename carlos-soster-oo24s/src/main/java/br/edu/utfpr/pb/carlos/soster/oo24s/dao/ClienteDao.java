package br.edu.utfpr.pb.carlos.soster.oo24s.dao;

import br.edu.utfpr.pb.carlos.soster.oo24s.model.Cliente;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Reserva;
import javax.persistence.Query;

public class ClienteDao extends GenericDao<Cliente, Long>{
    
    public ClienteDao(){
        super(Cliente.class);
    }
}
