package br.com.compass.avaliacao4.service;
import br.com.compass.avaliacao4.entities.AssociateEntity;
import br.com.compass.avaliacao4.entities.PartyEntity;
import br.com.compass.avaliacao4.entities.dto.request.RequestPartyDTO;
import br.com.compass.avaliacao4.entities.dto.request.RequestPoliticalAssociate;
import br.com.compass.avaliacao4.entities.dto.response.ResponseAssociateDTO;
import br.com.compass.avaliacao4.entities.dto.response.ResponsePartyDTO;
import br.com.compass.avaliacao4.exceptions.PartyNotFoundException;
import br.com.compass.avaliacao4.repository.PartyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartyService {
    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private ModelMapper modelMapper;

    public ResponsePartyDTO getById(Long id) {
        PartyEntity partyEntity = partyRepository.findById(id).orElseThrow(PartyNotFoundException::new);
        return modelMapper.map(partyEntity, ResponsePartyDTO.class);
    }

    public List<ResponsePartyDTO> get() {
       List<PartyEntity> allParties = partyRepository.findAll();
       List<ResponsePartyDTO> dtos = allParties.stream().map(partyEntity -> modelMapper.map(partyEntity, ResponsePartyDTO.class)).collect(Collectors.toList());
       return dtos;
   }

   public ResponsePartyDTO save(@Valid RequestPartyDTO request) {
        validationService.validateIdeology(request);
        PartyEntity partyEntity = modelMapper.map(request, PartyEntity.class);
        PartyEntity savedPartyEntity = partyRepository.save(partyEntity);
        return modelMapper.map(savedPartyEntity, ResponsePartyDTO.class);
    }

    public ResponsePartyDTO delete(Long id) {
        PartyEntity partyEntity = partyRepository.findById(id).orElseThrow(PartyNotFoundException::new);
        partyRepository.delete(partyEntity);
        return modelMapper.map(partyEntity, ResponsePartyDTO.class);
    }

    public void update(@Valid RequestPartyDTO request, Long id) {
        validationService.validateIdeology(request);
        PartyEntity partyEntity = partyRepository.findById(id).orElseThrow(PartyNotFoundException::new);
        modelMapper.map(request, partyEntity);
        partyRepository.save(partyEntity);
    }

    public List<PartyEntity> findAll() {
        List<PartyEntity> party = partyRepository.findAll();
        return party;
    }

    public List<ResponseAssociateDTO> getAllAssociatesById(Long id) {
        PartyEntity partyEntity = partyRepository.findById(id).orElseThrow(PartyNotFoundException::new);
        List<AssociateEntity> associates = partyEntity.getAssociates();
        return associates.stream().map(associateEntity -> modelMapper.map(
                associateEntity, ResponseAssociateDTO.class)).collect(Collectors.toList());
    }
}
