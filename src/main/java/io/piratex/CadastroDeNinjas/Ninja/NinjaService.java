package io.piratex.CadastroDeNinjas.Ninja;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {
    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Listar todos os ninjas
    public List<NinjaDTO> listarNinjas() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    //Listar ninjas por ID
    public NinjaDTO listarNinjaPorId(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    // Criar um novo ninja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    // Deletar o ninja - TEM QUE SER UM METODO VOID
    public void deletarNinjas(Long id) {
        ninjaRepository.deleteById(id);
    }

    public NinjaDTO alterarNinjas(Long id, NinjaDTO ninjaDTO) {
        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id);
        if (ninjaExistente.isPresent()) {
            NinjaModel ninja= ninjaRepository.getById(id);
            NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO);
            ninjaAtualizado.setId(id);
            if (ninjaAtualizado.getNome() == null) ninjaAtualizado.setNome(ninja.getNome());
            if (ninjaAtualizado.getIdade() == 0) ninjaAtualizado.setIdade(ninja.getIdade());
            if (ninjaAtualizado.getEmail() == null) ninjaAtualizado.setEmail(ninja.getEmail());
            if (ninjaAtualizado.getRank() == null) ninjaAtualizado.setRank(ninja.getRank());
            if (ninjaAtualizado.getImgUrl() == null) ninjaAtualizado.setImgUrl(ninja.getImgUrl());
            if (ninjaAtualizado.getMissoes() == null) ninjaAtualizado.setMissoes(ninja.getMissoes());
            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado);
            return ninjaMapper.map(ninjaSalvo);
        }else  {
            return null;
        }
    }
}
