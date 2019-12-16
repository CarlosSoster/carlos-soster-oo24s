package br.edu.utfpr.pb.carlos.soster.oo24s.model;

import br.edu.utfpr.pb.carlos.soster.oo24s.util.TipoContatoConverter;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contato")
public class Contato implements AbstractModel{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 25, nullable = false)
    private String telefone;
    
    @Enumerated(EnumType.STRING)
    private EOperadora operadora;
    
    //@Enumerated(EnumType.ORDINAL)
    @Convert(converter = TipoContatoConverter.class)
    private ETipoContato tipoContato;
    
    @ManyToOne()
    @JoinColumn(name = "usuario_id", referencedColumnName="id")
    private Usuario usuario;

    public Contato() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EOperadora getOperadora() {
        return operadora;
    }

    public void setOperadora(EOperadora operadora) {
        this.operadora = operadora;
    }

    public ETipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(ETipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Contato other = (Contato) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
