package br.com.compass.avaliacao4.controller;

import br.com.compass.avaliacao4.entities.AssociateEntity;
import br.com.compass.avaliacao4.entities.dto.request.RequestAssociateDTO;
import br.com.compass.avaliacao4.entities.dto.request.RequestPoliticalAssociate;
import br.com.compass.avaliacao4.entities.dto.response.ResponseAssociateDTO;
import br.com.compass.avaliacao4.repository.AssociateRepository;
import br.com.compass.avaliacao4.service.AssociateService;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/associados")
public class AssociateController {

    @Autowired
    private AssociateService associateService;

    @Autowired
    private AssociateRepository associateRepository;

    @GetMapping
    public ResponseEntity<List<ResponseAssociateDTO>> getAssociate() {
        List<ResponseAssociateDTO> responseAssociateDTOS = associateService.get();
        return ResponseEntity.ok(responseAssociateDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAssociateDTO> getAssociateById(@PathVariable Long id) {
        ResponseAssociateDTO responseAssociateDTO = associateService.getById(id);
        return ResponseEntity.ok(responseAssociateDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseAssociateDTO> postAssociate(@RequestBody @Valid RequestAssociateDTO request) {
        ResponseAssociateDTO responseAssociate = associateService.save(request);
        return ResponseEntity.ok(responseAssociate);
    }

    @PostMapping("/partidos")
    public ResponseEntity<Void> associateToParty(@RequestBody @Valid RequestPoliticalAssociate request) {
        associateService.associateToParty(request);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid RequestAssociateDTO request, @PathVariable Long id) {
        associateService.update(request, id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseAssociateDTO> deleteById(@PathVariable Long id) {
        associateService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{associadoId}/partidos/{partidoId}")
    public ResponseEntity<Void> deleteAssociateById(@PathVariable Long associadoId, @PathVariable Long partidoId) {
        associateService.deleteAssociateById(associadoId, partidoId);
        return ResponseEntity.noContent().build();
    }

}
