package org.sahthan.sahthan_v1.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="hangar")
public class Hangar {

    //primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private double altura;
    private double largura;
    private double comprimento;

    //classifica como enum no banco
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_enum")
    private StatusHangar statusHangar;

    //relacionamento com localidade
    @ManyToOne
    @JoinColumn(name = "localidade_id")
    private Localidade localidade;


    public Hangar(String nome, double altura, double largura, double comprimento, Localidade localidade, StatusHangar statusHangar) {
        this.nome = nome;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.localidade = localidade;
        this.statusHangar = statusHangar;
    }

    public Hangar() {
    }

    //getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public StatusHangar getStatusHangar() {
        return statusHangar;
    }

    public void setStatusHangar(StatusHangar statusHangar) {
        this.statusHangar = statusHangar;
    }

    //converter classe para seu nome
    @Override
    public String toString() {

        return this.nome;
    }
}
