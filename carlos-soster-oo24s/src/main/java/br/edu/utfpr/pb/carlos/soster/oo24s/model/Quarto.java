package br.edu.utfpr.pb.carlos.soster.oo24s.model;

import br.edu.utfpr.pb.carlos.soster.oo24s.util.TipoQuartoConverter;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "quarto")
public class Quarto implements AbstractModel{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @NotEmpty(message = "O campo 'numero' deve ser preenchido.")
    @Column(name = "numero", nullable = false)
    private Long numero;
    
    @Column(name = "tipo_quarto", nullable = false)
    @Convert(converter = TipoQuartoConverter.class)
    private ETipoQuarto tipoQuarto;
    
    @NotEmpty(message = "O campo 'quantidade de camas' deve ser preenchido.")
    @Column(name = "quantidade_camas", nullable = false)
    private Integer qtdeCamas;
    
    @NotEmpty(message = "O campo 'quantidade de pessoas' deve ser preenchido.")
    @Column(name = "quantidade_pessoas", nullable = false)
    private Integer qtdePessoas;
    
    @NotEmpty(message = "O campo 'valor da diaria' deve ser preenchido.")
    @Column(name = "valor_diaria", nullable = false)
    private Double valorDiaria;

    public Quarto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public ETipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(ETipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public Integer getQtdeCamas() {
        return qtdeCamas;
    }

    public void setQtdeCamas(Integer qtdeCamas) {
        this.qtdeCamas = qtdeCamas;
    }

    public Integer getQtdePessoas() {
        return qtdePessoas;
    }

    public void setQtdePessoas(Integer qtdePessoas) {
        this.qtdePessoas = qtdePessoas;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Quarto other = (Quarto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numero.toString();
    }
}
