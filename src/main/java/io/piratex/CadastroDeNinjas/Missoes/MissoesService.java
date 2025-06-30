package io.piratex.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    // Listar missoes
    public List<MissoesModel> listarMissoes() {
        return missoesRepository.findAll();
    }

    // Criar missoes
    public MissoesModel criarMissoes(MissoesModel missao) {
        return missoesRepository.save(missao);
    }

    // Deletar Missao
    public void deletarMissoes(Long id) {
        missoesRepository.deleteById(id);
    }

}
