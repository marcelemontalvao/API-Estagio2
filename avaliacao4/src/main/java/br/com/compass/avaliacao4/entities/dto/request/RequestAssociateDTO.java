package br.com.compass.avaliacao4.entities.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RequestAssociateDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String cargoPolitico;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @NotBlank
    private String sexo;

}
