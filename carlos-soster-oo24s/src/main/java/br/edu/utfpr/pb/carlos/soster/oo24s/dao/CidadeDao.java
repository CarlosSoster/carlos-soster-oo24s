/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.carlos.soster.oo24s.dao;

import br.edu.utfpr.pb.carlos.soster.oo24s.model.Cidade;


/**
 *
 * @author Carlos
 */
public class CidadeDao extends GenericDao<Cidade, Long>{
    
    public CidadeDao(){
        super(Cidade.class);
    }
}
