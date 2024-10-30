package br.com.fight.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fight.Entity.Academia;
import br.com.fight.Entity.Lutador;
import br.com.fight.Service.AcademiaService;

import java.util.List;
import java.util.Optional;

import br.com.fight.VO.AcademiaVO;

@RestController
@RequestMapping("/academia")

public class AcademiaController {

    @Autowired
    AcademiaService service;

    @GetMapping("/academiaAll")
    public ResponseEntity<List<Academia>> getAllAcademia() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("cadastro")
    public ResponseEntity<?> adicionarAcademia(@RequestBody AcademiaVO entity) {
        Academia academia = service.adicionarAcademia(entity);
        if (academia != null) {
            return new ResponseEntity<>("Academia Criada com sucesso", HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAcademia(@PathVariable Long id, @RequestBody AcademiaVO academia) {
        Academia atualizada = service.updateAcademia(id, academia);
        if (atualizada != null) {
            return ResponseEntity.ok(atualizada);
        }
        return ResponseEntity.badRequest().body("Não encontramos essa Academia");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> excluirAcademia(@PathVariable Long id) {
        return (service.removerAcademia(id) ? 
        ResponseEntity.ok("Academia removida") : 
        ResponseEntity.badRequest().body("Academia não encontrada"));
    }

    @GetMapping("/buscarAcademia/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Academia> academia = service.findByAcademia(id);
        if (academia != null) {
            return ResponseEntity.ok(academia);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/alunos/{academiaId}")
    public ResponseEntity<List<Lutador>> getLutadoresByAcademia(@PathVariable Long academiaId) {
        List<Lutador> alunos = service.findAllLutadoresByAcademia(academiaId);
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }
}