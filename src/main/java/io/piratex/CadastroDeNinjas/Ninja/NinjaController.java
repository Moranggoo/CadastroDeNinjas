package io.piratex.CadastroDeNinjas.Ninja;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Primeira rota da API";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinjas(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    // Listar todos os ninjas (READ)
    @GetMapping("/listar")
    public ResponseEntity<List> listarNinjas() {
        return ResponseEntity.ok(ninjaService.listarNinjas());
    }

    // Listar os ninjas por id (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listarNinjaPorId(id);
        if (ninja != null) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(ninja);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja não encontrado");
        }
    }

    // Atualizar um ninja (UPDATE)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarNinjas(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado) {
        if (ninjaService.listarNinjaPorId(id) != null) {
            NinjaDTO ninja = ninjaService.alterarNinjas(id, ninjaAtualizado);
            return ResponseEntity.ok("O " + ninja.getNome() + " foi atualizado.");
        }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com ID: " + id + " não existe.");
        }
    }

    // Deletar um ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjas(@PathVariable Long id) {
        if (ninjaService.listarNinjaPorId(id) != null) {
            ninjaService.deletarNinjas(id);
            return ResponseEntity.ok("Ninja com o ID: " + id + " deletado com sucesso!");
        }else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND)
                 .body("O ninja com ID: " + id + " não existe.");
        }
    }
}
