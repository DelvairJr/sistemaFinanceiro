package br.com.sistemafinanceiro.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.sistemafinanceiro.model.GrupoFinanceiro;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Junior
 */
@Entity
public class Despesas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String mes;

    @Column(nullable = false)
    private String descricao;

    @OneToOne
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Double valor;

    @OneToOne
    private GrupoFinanceiro gpFinanceiro;

//    @Column(nullable = false)
//    @ManyToOne
//    private GrupoFinanceiro grupoFinanceiro;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public GrupoFinanceiro getGpFinanceiro() {
        return gpFinanceiro;
    }

    public void setGpFinanceiro(GrupoFinanceiro gpFinanceiro) {
        this.gpFinanceiro = gpFinanceiro;
    }

}
