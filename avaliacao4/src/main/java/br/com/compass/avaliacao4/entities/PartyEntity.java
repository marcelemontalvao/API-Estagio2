package br.com.compass.avaliacao4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String sigla;
    private String ideologia;
    private Date dataFundacao;

    @OneToMany(mappedBy = "party")
    private List<AssociateEntity> associates;

}
