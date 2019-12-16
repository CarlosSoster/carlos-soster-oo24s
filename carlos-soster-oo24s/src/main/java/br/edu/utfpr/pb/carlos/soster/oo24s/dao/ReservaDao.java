package br.edu.utfpr.pb.carlos.soster.oo24s.dao;

import br.edu.utfpr.pb.carlos.soster.oo24s.model.Reserva;
import javax.persistence.Query;

public class ReservaDao extends GenericDao<Reserva, Long>{
    
    public ReservaDao(){
        super(Reserva.class);
    }
}
