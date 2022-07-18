package br.com.compass.avaliacao4.repository;
import br.com.compass.avaliacao4.entities.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<PartyEntity, Long> {
    List<PartyEntity> findbyIdeologia(String ideologia);

}
