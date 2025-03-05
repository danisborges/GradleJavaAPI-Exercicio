package application.model;

import application.record.LivroDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String generos;
    private String autores;

    public Livro(LivroDTO dto) {
        this.titulo = dto.titulo();
        this.generos = dto.generos();
        this.autores = dto.autores();
    }
}
