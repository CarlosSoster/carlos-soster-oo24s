package br.edu.utfpr.pb.carlos.soster.oo24s.util;

import br.edu.utfpr.pb.carlos.soster.oo24s.model.ETipoQuarto;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TipoQuartoConverter implements 
        AttributeConverter<ETipoQuarto, Integer>{

    @Override
    public Integer convertToDatabaseColumn(ETipoQuarto attribute) {
        return attribute.getId();
    }

    @Override
    public ETipoQuarto convertToEntityAttribute(Integer dbData) {
        return ETipoQuarto.findById(dbData);
    }
    
}
