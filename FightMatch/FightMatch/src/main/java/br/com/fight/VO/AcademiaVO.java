package br.com.fight.VO;

import java.util.List;

import lombok.Data;

@Data
public class AcademiaVO {
    private String id;
    private String nome;
    private String mestre;
    private String arteMarcial;
    private String telefone;
    private String email;
    private List<LutadorVO> alunos;

}
