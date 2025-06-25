package io.piratex.CadastroDeNinjas.Ninja;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Primeira rota da API";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinjas() {
        return "Ninja criado";
    }

    // Listar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    // Listar os ninjas por id (READ)
    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjasPorId(@PathVariable Long id) {
        return ninjaService.listarNinjaPorId(id);
    }

    // Atualizar um ninja (UPDATE)
    @PostMapping("/alterar")
    public String alterarNinjas() {
        return "Ninja alterado";
    }

    // Deletar um ninja (DELETE)
    @DeleteMapping("/deletar")
    public String deletarNinjas() {
        return "Ninja deletado";
    }
}
