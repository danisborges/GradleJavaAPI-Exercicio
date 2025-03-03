package application.model;

import application.record.LivroDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Livro {

    @Id
    private int id;
    private String titulo;
    private String generos;
    private String autores;

    public Livro(LivroDTO dto) {
        this.id = dto.id();
        this.titulo = dto.titulo();
        this.generos = dto.generos();
        this.autores = dto.autores();
    }
}
