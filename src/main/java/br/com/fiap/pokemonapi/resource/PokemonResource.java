package br.com.fiap.pokemonapi.resource;

import br.com.fiap.pokemonapi.bo.PokemonBO;
import br.com.fiap.pokemonapi.to.PokemonTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonResource {

    private final PokemonBO bo = new PokemonBO();

    @GetMapping
    public ResponseEntity<List<PokemonTO>> findAll() {
        List<PokemonTO> pokemons = bo.findAll();
        if (pokemons.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<PokemonTO> findByCodigo(@PathVariable Long codigo) {
        PokemonTO pokemon = bo.findByCodigo(codigo);
        if (pokemon == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pokemon);
    }

    @PostMapping
    public ResponseEntity<PokemonTO> save(@Valid @RequestBody PokemonTO pokemon) {
        PokemonTO salvo = bo.save(pokemon);
        if (salvo == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<PokemonTO> update(@PathVariable Long codigo, @Valid @RequestBody PokemonTO pokemon) {
        PokemonTO atualizado = bo.update(codigo, pokemon);
        if (atualizado == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.status(201).body(atualizado);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable Long codigo) {
        if (!bo.delete(codigo)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}