package br.edu.utfpr.pb.carlos.soster.oo24s.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "reserva")
public class Reserva implements AbstractModel{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="quarto_id", referencedColumnName = "id")
    private Quarto quarto;
    
    @ManyToOne()
    @JoinColumn(name="cliente_id", referencedColumnName = "id")
    private Cliente cliente;
    
    @ManyToOne()
    @JoinColumn(name="usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    
    @Column(name="valor_diaria" ,nullable = false)
    private Double valorDiaria;
    
    @Column(name="data_reserva" ,nullable = false)
    private LocalDate dataReserva;
    
    @Column(name="data_entrada")
    private LocalDate dataEntrada;
    
    @Column(name="data_saida")
    private LocalDate dataSaida;
    
    @Column(length = 100)
    private String motivo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "reserva_hospedes", joinColumns = {
                    @JoinColumn(name = "reserva_id") }, inverseJoinColumns = { @JoinColumn(name = "cliente_id") })
    private Set<Cliente> hospedes;

    @OneToMany(mappedBy = "reserva", 
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            fetch = FetchType.LAZY)
    private List<ReservaProduto> reservaProdutos;

    @Transient
    private Double valorTotal;

    public Double getValorTotal(){
        return reservaProdutos.stream().mapToDouble(vp -> vp.getValor() *
                vp.getQuantidade()).sum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Set<Cliente> getHospedes() {
        return hospedes;
    }

    public void setHospedes(Set<Cliente> hospedes) {
        this.hospedes = hospedes;
    }

    public List<ReservaProduto> getReservaProdutos() {
        return reservaProdutos;
    }

    public void setReservaProdutos(List<ReservaProduto> reservaProdutos) {
        this.reservaProdutos = reservaProdutos;
    }
    
    public void addHospede(Cliente hospede) {
            if (hospedes == null) {
                    hospedes = new HashSet<Cliente>();
            }
            hospedes.add(hospede);
    }
    
}
