package br.com.fight.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Data
@Entity
@Table(name="lutador")
public class Lutador{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String genero;
    private Double peso;
    private Integer idade;
    private String graduacao;
    private Boolean disponivelLuta;
    
    @ManyToOne
    @JoinColumn(name = "id_academia", nullable = false)
    @JsonBackReference
    private Academia academia;

    public Lutador(){}

    public Lutador(Academia academia){
        this.academia = academia;
    }

}