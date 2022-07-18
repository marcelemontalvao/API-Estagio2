package br.com.compass.avaliacao4.controller;

import br.com.compass.avaliacao4.entities.dto.request.RequestPartyDTO;
import br.com.compass.avaliacao4.entities.dto.response.ResponseAssociateDTO;
import br.com.compass.avaliacao4.entities.dto.response.ResponsePartyDTO;
import br.com.compass.avaliacao4.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/partidos")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @GetMapping
    public ResponseEntity<List<ResponsePartyDTO>> getParty() {
        List<ResponsePartyDTO> responsePartyDTOS = partyService.get();
        return ResponseEntity.ok(responsePartyDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePartyDTO> getPartyById(@PathVariable Long id) {
        ResponsePartyDTO responsePartyDTO = partyService.getById(id);
        return ResponseEntity.ok(responsePartyDTO);
    }

    @GetMapping("/{id}/associados")
    public ResponseEntity<List<ResponseAssociateDTO>> getAllAssociatesById(@PathVariable Long id) {
        List<ResponseAssociateDTO> response = partyService.getAllAssociatesById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponsePartyDTO> postParty(@RequestBody @Valid RequestPartyDTO request) {
        ResponsePartyDTO responseParty = partyService.save(request);
        return ResponseEntity.ok(responseParty);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid RequestPartyDTO request, @PathVariable Long id) {
        partyService.update(request, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePartyDTO> deleteById(@PathVariable Long id) {
        partyService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
