package br.com.compass.avaliacao4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cargoPolitico;
    private Date dataNascimento;
    private String sexo;
    @ManyToOne
    private PartyEntity party;
}
