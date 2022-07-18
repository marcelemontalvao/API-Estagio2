package br.com.compass.avaliacao4.entities.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class RequestPartyDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String sigla;

    @NotBlank
    private String ideologia;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataFundacao;
}
