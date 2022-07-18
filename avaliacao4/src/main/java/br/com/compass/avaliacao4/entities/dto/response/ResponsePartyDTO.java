package br.com.compass.avaliacao4.entities.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePartyDTO {

    private Long id;

    private String nome;

    private String sigla;

    private String ideologia;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataFundacao;
}
