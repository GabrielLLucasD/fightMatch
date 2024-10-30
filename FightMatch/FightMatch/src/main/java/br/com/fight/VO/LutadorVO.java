package br.com.fight.VO;

import lombok.Data;

@Data
public class LutadorVO {
    private Long id;
    private String nome;
    private String genero;
    private Double peso;
    private Integer idade;
    private String graduacao;
    private Long idAcademia;
    private Boolean disponivelLuta;

}
