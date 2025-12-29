package io.piratex.CadastroDeNinjas.Ninja;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota retorna uma mensagem de boas vindas quem a acessa")
    public String boasVindas() {
        return "Primeira rota da API";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja a partir do NinjaDTO", description = "Cria um novo ninja e inclui no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criaçao do ninja")
    })
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
    @Operation(summary = "Lista um ninja por ID", description = "Usa o id como parametro para listar um ninja especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado, verifique o id")
    })
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
    @PatchMapping("/alterar/{id}")
    @Operation(summary = "Altera o ninja por id", description = "Usa o id como parametro para alterar um ninja especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado, verifique o id")
    })
    public ResponseEntity<String> alterarNinjas(@Parameter(description = "Usuario manda o id no caminho da requisicao") @PathVariable Long id,@Parameter(description = "Usuario manda os dados de alteracao do ninja no corpo da requisicao") @RequestBody NinjaDTO ninjaAtualizado) {
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
