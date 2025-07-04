package io.piratex.CadastroDeNinjas.Ninja;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {

    private final NinjaService ninjaService;

    public NinjaControllerUi(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }


    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas"; // Tem que retornar o nome da pagina que renderiza
    }


    @GetMapping("/deletar/{id}")
    public String deletarNinjas(@PathVariable Long id) {
        ninjaService.deletarNinjas(id);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarNinjasPorId(@PathVariable Long id, Model model, Model aux) {
        NinjaDTO ninja = ninjaService.listarNinjaPorId(id);
        model.addAttribute("ninja", ninja);

        if (ninja.getMissoes() == null) {
            aux.addAttribute("mensagem", "O ninja não possui uma missão!!");
        }

        return "detalhesNinja";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioDeCriacaoDoNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        return "adicionarNinja";
    }

    @PostMapping("/salvar")
    public String salvarNinjas(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja criado com sucesso");
        return "redirect:/ninjas/ui/listar";
    }
}
