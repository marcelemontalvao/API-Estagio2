package br.com.compass.avaliacao4.repository;
import br.com.compass.avaliacao4.Enum.CargoPolitico;
import br.com.compass.avaliacao4.entities.AssociateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssociateRepository extends JpaRepository<AssociateEntity, Long> {
    List<AssociateEntity> findbyNome(String nome);
    List<AssociateEntity> findbyCargoPolitico(CargoPolitico cargoPolitico);
}
