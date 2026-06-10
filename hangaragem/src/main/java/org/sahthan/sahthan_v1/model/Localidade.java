package org.sahthan.sahthan_v1.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="localidade")

public class Localidade {

    //primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nomeAeroporto;
    private int nPistas;
    private String cidade;
    private String uf;

    //mapeando array de hangares para o banco
    @OneToMany(mappedBy = "localidade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Hangar> hangares;

    public Localidade(String nomeAeroporto, int nPistas, String cidade, String uf) {
        this.nomeAeroporto = nomeAeroporto;
        this.nPistas = nPistas;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Localidade() {
    }

    //getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeAeroporto() {
        return nomeAeroporto;
    }

    public void setNomeAeroporto(String nomeAeroporto) {
        this.nomeAeroporto = nomeAeroporto;
    }

    public int getNPistas() {
        return nPistas;
    }

    public void setNPistas(int nPistas) {
        this.nPistas = nPistas;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public List<Hangar> getHangares() {
        return hangares;
    }

    public void setHangares(List<Hangar> hangares) {
        this.hangares = hangares;
    }

    public void addHangar(Hangar hangar){
        this.hangares.add(hangar);
    }

    //converter classe para seu nome
    @Override
    public String toString() {

        return this.nomeAeroporto;
    }
}
