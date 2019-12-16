package br.edu.utfpr.pb.carlos.soster.oo24s.model;

import java.io.Serializable;

public interface AbstractModel <ID extends  Serializable> 
            extends Serializable{
    ID getId();
}
