package br.com.compass.avaliacao4.service;

import br.com.compass.avaliacao4.Enum.CargoPolitico;
import br.com.compass.avaliacao4.Enum.Ideologia;
import br.com.compass.avaliacao4.Enum.Sexo;
import br.com.compass.avaliacao4.entities.dto.request.RequestAssociateDTO;
import br.com.compass.avaliacao4.entities.dto.request.RequestPartyDTO;
import br.com.compass.avaliacao4.exceptions.IdeologyInvalidException;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.Arrays;

@Service
public class ValidationService {
    public void validateIdeology(@Valid RequestPartyDTO request) {
        boolean isValid = Arrays.stream(Ideologia.values()).anyMatch(ideologiaEnum -> ideologiaEnum.getValue().equals(request.getIdeologia()));
        if(!isValid) {
            throw new IdeologyInvalidException();
        }
    }

    public void validateGender(@Valid RequestAssociateDTO request) {
        boolean isValid = Arrays.stream(Sexo.values()).anyMatch(sexoEnum -> sexoEnum.getValue().equals(request.getSexo()));
        if(!isValid) {
            throw new IdeologyInvalidException();
        }
    }
    public void validateOffice(@Valid RequestAssociateDTO request) {
        boolean isValid = Arrays.stream(CargoPolitico.values()).anyMatch(cargoPoliticoEnum -> cargoPoliticoEnum.getValue().equals(request.getCargoPolitico()));
        if(!isValid) {
            throw new IdeologyInvalidException();
        }
    }

}
