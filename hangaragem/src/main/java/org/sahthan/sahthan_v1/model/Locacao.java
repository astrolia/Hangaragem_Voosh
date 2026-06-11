package org.sahthan.sahthan_v1.model;

import jakarta.persistence.*;


@Entity
@Table(name = "locacao")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Localidade localidade;
    @ManyToOne
    private Hangar hangar;
    @ManyToOne
    private Aeronave aeronave;


    private String checkIn;
    private String checkOut;
    private double valor;

    public Locacao() {
    }

    public Locacao(Localidade localidade, Hangar hangar, Aeronave aeronave, String checkIn, String checkOut, double valor) {
        this.localidade = localidade;
        this.hangar = hangar;
        this.aeronave = aeronave;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.valor = valor;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public Hangar getHangar() {
        return hangar;
    }

    public void setHangar(Hangar hangar) {
        this.hangar = hangar;
    }

    public Aeronave getAeronave() {
        return aeronave;
    }

    public void setAeronave(Aeronave aeronave) {
        this.aeronave = aeronave;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
