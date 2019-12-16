package br.edu.utfpr.pb.carlos.soster.oo24s.dao;

import br.edu.utfpr.pb.carlos.soster.oo24s.model.ETipoProduto;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Produto;
import java.util.List;
import javax.persistence.Query;

public class ProdutoDao extends GenericDao<Produto, Long> {

    public ProdutoDao() {
        super(Produto.class);
    }
    
    public List<Produto> findProdutoByTipo(ETipoProduto tipo) {
        Query query = em.createQuery("Select p "
                + "FROM Produto p "
                + "WHERE p.tipoProduto = :tipo");
        query.setParameter("tipo", tipo);
        return (List<Produto>) query.getResultList();
    }
}
