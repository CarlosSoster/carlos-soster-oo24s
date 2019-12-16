/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.carlos.soster.oo24s.dao;

import br.edu.utfpr.pb.carlos.soster.oo24s.model.ETipoProduto;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.ReservaProduto;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
public class ReservaProdutoDao extends GenericDao<ReservaProduto, Long>{
    
    public ReservaProdutoDao(){
        super(ReservaProduto.class);
    }
    
    public List<ReservaProduto> findProdutoByTipo(ETipoProduto tipo) {
        Query query = em.createQuery("Select rp "
                + "FROM ReservaProduto rp "
                + "JOIN Produto p on p = rp.produto " 
                + "WHERE p.tipoProduto=:tipo");
        query.setParameter("tipo", tipo);
        return (List<ReservaProduto>) query.getResultList();
    }
}
