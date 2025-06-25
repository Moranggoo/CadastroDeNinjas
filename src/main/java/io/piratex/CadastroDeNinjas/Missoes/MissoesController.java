package io.piratex.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    @GetMapping("/rotamissoes")
    public String rotaMissoes() {
        return "Primeira rota das missoes";
    }

    @PostMapping("/criar")
    public String criarMissoes() {
        return "Missao criada";
    }

    @GetMapping("/listar")
    public String listarMissoes() {
        return "Missoes: ";
    }

    @PostMapping("/alterar")
    public String alterarMissoes() {
        return "Missao alterada";
    }

    @DeleteMapping("/deletar")
    public String deletarMissoes() {
        return "Missao deletada";
    }

}
