package br.com.fiap.pokemonapi.bo;

import br.com.fiap.pokemonapi.dao.PokemonDAO;
import br.com.fiap.pokemonapi.to.PokemonTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonBO {

    private final PokemonDAO dao = new PokemonDAO();

    public List<PokemonTO> findAll() { return dao.findAll(); }

    public PokemonTO findByCodigo(Long codigo) {
        if (codigo == null || codigo <= 0) return null;
        return dao.findByCodigo(codigo);
    }

    public PokemonTO save(PokemonTO pokemon) {
        if (pokemon == null) return null;
        return dao.save(pokemon);
    }

    public PokemonTO update(Long codigo, PokemonTO pokemon) {
        if (codigo == null || codigo <= 0 || pokemon == null) return null;
        if (dao.findByCodigo(codigo) == null) return null;
        return dao.update(codigo, pokemon);
    }

    public boolean delete(Long codigo) {
        if (codigo == null || codigo <= 0) return false;
        return dao.delete(codigo);
    }
}