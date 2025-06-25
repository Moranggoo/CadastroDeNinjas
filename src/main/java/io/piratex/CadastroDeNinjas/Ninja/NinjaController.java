package io.piratex.CadastroDeNinjas.Ninja;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

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
    public String listarNinjas() {
        return "Ninjas: ";
    }

    // Listar os ninjas por id (READ)
    @GetMapping("/listar/{id}")
    public String listarNinjasPorId() {
        return "Ninja criado";
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
