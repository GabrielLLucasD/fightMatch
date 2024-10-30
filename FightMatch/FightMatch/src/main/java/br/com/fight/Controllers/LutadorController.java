package br.com.fight.Controllers;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fight.Entity.Lutador;
import br.com.fight.Service.LutadorService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fight.VO.LutadorVO;

import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/lutador")
public class LutadorController {

  @Autowired
  LutadorService service;

  @GetMapping("/lutadorAll")
  public ResponseEntity<List<Lutador>> getAllLutador() {
    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }

  @GetMapping("/busca/{id}")
  public ResponseEntity<?> buscaLutador(@PathVariable Long id) {
    Optional<Lutador> lutador = service.findByLutador(id);
    if(lutador != null){
      return ResponseEntity.ok(lutador); 
    }
    return ResponseEntity.badRequest().body("Não foi possivel achar um lutador com esse id");
  }


  @GetMapping("/busca-mulheres")
  public ResponseEntity<List<Lutador>> buscaMulheres(){
    return new ResponseEntity<>(service.findAllByGenero("Feminino"), HttpStatus.OK);
  }
    
  @GetMapping("/busca-homens")
    public ResponseEntity<List<Lutador>> buscaHomens(){
        return new ResponseEntity<>(service.findAllByGenero("Masculino"), HttpStatus.OK);
    }
    

  @PostMapping("/cadastro")
  public ResponseEntity<?> adicionarLutador(@RequestBody  LutadorVO entity) {
      Lutador lutador = service.adicionarLutador(entity);
      if(lutador != null){
        return new ResponseEntity<>("Lutador criado com sucesso", HttpStatus.OK);
      }
      
      return ResponseEntity.badRequest().build();
  }

  @PutMapping("update/{id}")
  public ResponseEntity<?> updateLutador(@PathVariable Long id, @RequestBody LutadorVO entity) {
    Lutador atualizado = service.updateLutador(id, entity);
    if(atualizado != null){
       return ResponseEntity.ok(entity);
      }
      return ResponseEntity.badRequest().build();
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> removerLutador(@PathVariable Long id){
    return (service.removerLutador(id)) ?
    ResponseEntity.ok("Lutador removido") : 
    ResponseEntity.badRequest().body("Lutador não encontrado");
  }

  @GetMapping("casa-lutas/{id}")
  public ResponseEntity<?> casarLutas(@PathVariable Long id){
    List<Lutador> lutadoresCompativeis = service.casarLutas(id);

    if(lutadoresCompativeis.isEmpty()){
      return ResponseEntity.badRequest().body("Não encontramos Lutadores Disponíveis");
    }
    return ResponseEntity.ok(lutadoresCompativeis);
  }
}
