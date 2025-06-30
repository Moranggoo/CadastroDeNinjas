package io.piratex.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/rotamissoes")
    public String rotaMissoes() {
        return "Primeira rota das missoes";
    }

    @PostMapping("/criar")
    public MissoesModel criarMissoes(@RequestBody MissoesModel missoesModel) {
        return missoesService.criarMissoes(missoesModel);
    }

    @GetMapping("/listar")
    public List<MissoesModel> listarMissoes() {
        return missoesService.listarMissoes();
    }

    @PostMapping("/alterar")
    public String alterarMissoes() {
        return "Missao alterada";
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarMissoes(@PathVariable Long id) {
        missoesService.deletarMissoes(id);
    }

}
