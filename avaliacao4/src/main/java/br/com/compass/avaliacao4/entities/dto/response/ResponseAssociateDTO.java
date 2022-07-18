package br.com.compass.avaliacao4.entities.dto.response;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.util.Date;

@Data
public class ResponseAssociateDTO {

    private Long id;

    private String nome;

    private String cargoPolitico;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    private String sexo;



}
