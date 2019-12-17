package br.edu.utfpr.pb.carlos.soster.oo24s.dao;

import br.edu.utfpr.pb.carlos.soster.oo24s.model.Quarto;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Reserva;
import java.time.LocalDate;
import javax.persistence.Query;

public class ReservaDao extends GenericDao<Reserva, Long>{
    
    public ReservaDao(){
        super(Reserva.class);
    }
    
    public Boolean validateQuarto(LocalDate dataEntrada, LocalDate dataSaida, Quarto quarto) {
        Query query = em.createQuery("Select r "
                + "FROM Reserva r "
                + "WHERE r.quarto = :quarto " 
                + "AND (r.dataEntrada BETWEEN :dataEntrada AND :dataSaida "
                + "OR r.dataSaida BETWEEN :dataEntrada AND :dataSaida ) ");
        query.setParameter("quarto", quarto);
        query.setParameter("dataEntrada", dataEntrada);
        query.setParameter("dataSaida", dataSaida);
        
        return query.getResultList().isEmpty();
    }
}
