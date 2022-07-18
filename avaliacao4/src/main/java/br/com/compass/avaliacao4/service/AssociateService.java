package br.com.compass.avaliacao4.service;

import br.com.compass.avaliacao4.entities.AssociateEntity;
import br.com.compass.avaliacao4.entities.PartyEntity;
import br.com.compass.avaliacao4.entities.dto.request.RequestAssociateDTO;
import br.com.compass.avaliacao4.entities.dto.request.RequestPoliticalAssociate;
import br.com.compass.avaliacao4.entities.dto.response.ResponseAssociateDTO;
import br.com.compass.avaliacao4.exceptions.AssociateNotFoundException;
import br.com.compass.avaliacao4.exceptions.PartyNotFoundException;
import br.com.compass.avaliacao4.repository.AssociateRepository;
import br.com.compass.avaliacao4.repository.PartyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociateService {
    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseAssociateDTO getById(Long id) {
        AssociateEntity associateEntity = associateRepository.findById(id).orElseThrow(AssociateNotFoundException::new);
        return modelMapper.map(associateEntity, ResponseAssociateDTO.class);
    }

    public List<ResponseAssociateDTO> get() {
        List<AssociateEntity> allAssociates = associateRepository.findAll();
        List<ResponseAssociateDTO> dtos = allAssociates.stream().map(associateEntity -> modelMapper.map(associateEntity, ResponseAssociateDTO.class)).collect(Collectors.toList());
        return dtos;
    }

    public ResponseAssociateDTO save(@Valid RequestAssociateDTO request) {
        validationService.validateGender(request);
        validationService.validateOffice(request);
        AssociateEntity associateEntity = modelMapper.map(request, AssociateEntity.class);
        AssociateEntity savedAssociateEntity = associateRepository.save(associateEntity);
        return modelMapper.map(savedAssociateEntity, ResponseAssociateDTO.class);
    }

    public ResponseAssociateDTO delete(Long id) {
        AssociateEntity associateEntity = associateRepository.findById(id).orElseThrow(AssociateNotFoundException::new);
        associateRepository.deleteById(id);
        return null;
    }

    public void update(@Valid RequestAssociateDTO request, Long id) {
        validationService.validateGender(request);
        validationService.validateOffice(request);
        AssociateEntity associateEntity = associateRepository.findById(id).orElseThrow(AssociateNotFoundException::new);
        modelMapper.map(request, associateEntity);
        associateRepository.save(associateEntity);
    }

    public void associateToParty(RequestPoliticalAssociate request) {
        Long partyId = request.getPartidoId();
        Long associateId = request.getAssociadoId();
        associateRepository.findById(associateId).orElseThrow(AssociateNotFoundException::new);
        associateRepository.findById(partyId).orElseThrow(PartyNotFoundException::new);

        AssociateEntity associateEntity = associateRepository.getReferenceById(associateId);

        if(associateEntity.getParty() == null) {
            PartyEntity referenceById = partyRepository.getReferenceById(partyId);
            associateEntity.setParty(referenceById);
            associateRepository.save(associateEntity);
        }
    }

    public void deleteAssociateById(Long associadoId, Long partidoId) {
        partyRepository.findById(partidoId).orElseThrow(PartyNotFoundException::new);
        associateRepository.findById(associadoId).orElseThrow(AssociateNotFoundException::new);

        AssociateEntity referenceByIdAssociate = associateRepository.getReferenceById(associadoId);
        PartyEntity referenceByIdParty = partyRepository.getReferenceById(partidoId);
        boolean equals = referenceByIdAssociate.getParty().equals(referenceByIdParty);

        if (equals) {
            referenceByIdAssociate.setParty(null);
            associateRepository.save(referenceByIdAssociate);
        }
    }
}
