package br.edu.utfpr.pb.carlos.soster.oo24s.util;

import br.edu.utfpr.pb.carlos.soster.oo24s.model.ETipoProduto;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TipoProdutoConverter implements 
        AttributeConverter<ETipoProduto, Integer>{

    @Override
    public Integer convertToDatabaseColumn(ETipoProduto attribute) {
        return attribute.getId();
    }

    @Override
    public ETipoProduto convertToEntityAttribute(Integer dbData) {
        return ETipoProduto.findById(dbData);
    }
    
}
