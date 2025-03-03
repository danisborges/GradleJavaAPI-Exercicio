package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Livro;
import application.record.LivroDTO;
import application.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepo;

    @GetMapping
    public Iterable<LivroDTO> list() {
        return livroRepo.findAll().stream().map(LivroDTO::new).toList();
    }

    @GetMapping("/{id}")
    public LivroDTO getOne(@PathVariable int id) {
        Optional<Livro> resultado = livroRepo.findById(id);
        if(resultado.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro não encontrado"
            );
        }
        return new LivroDTO(resultado.get());
    }

    @PostMapping
    public LivroDTO insert(@RequestBody LivroDTO novoLivro) {
        Livro novosDados = new Livro(novoLivro);
        Livro livroSalvo = livroRepo.save(novosDados);
        LivroDTO retorno = new LivroDTO(livroSalvo); 
        return retorno;

        // return new LivroDTO(livroRepo.sabe(new Livro(novaLivro)));
    }

    @PutMapping("/{id}")
    public LivroDTO update(@RequestBody LivroDTO dados, @PathVariable int id) {
        Optional<Livro> resultado = livroRepo.findById(id);
        
        if(resultado.isPresent()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro não encontrado"
            );
        }
        resultado.get().setTitulo(dados.titulo());
        return new LivroDTO(livroRepo.save(resultado.get()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        if(!livroRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro não encontrado"
            );
        }
        livroRepo.deleteById(id);
    }
}
