/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.carlos.soster.oo24s.dao;

import br.edu.utfpr.pb.carlos.soster.oo24s.model.Contato;


/**
 *
 * @author Carlos
 */
public class ContatoDao extends GenericDao<Contato, Long>{
    public ContatoDao(){
        super(Contato.class);
    }
}
