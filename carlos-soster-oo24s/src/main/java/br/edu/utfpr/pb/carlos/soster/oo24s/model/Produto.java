package br.edu.utfpr.pb.carlos.soster.oo24s.model;

import br.edu.utfpr.pb.carlos.soster.oo24s.util.TipoProdutoConverter;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto implements AbstractModel{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "O campo nome deve ser preenchido.")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    
    @NotEmpty(message = "O campo descrição deve ser preenchido.")
    @Column(name = "descricao", length = 500, nullable = false)
    private String descricao;
    
    @DecimalMin(value = "0.01", 
            message = "O valor deve ser maior que R$ 0.00.")
    @Column(name = "valor")
    private Double valor;
    
    @NotNull(message = "O campo 'categoria' deve ser selecionado.")
    @ManyToOne()
    @JoinColumn(name="categoria_id", referencedColumnName = "id")
    private Categoria categoria;
    
    @Column(name = "tipo_produto")
    @Convert(converter = TipoProdutoConverter.class)
    private ETipoProduto tipoProduto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public ETipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(ETipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
}
