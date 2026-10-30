package br.com.fiap.pokemonapi.to;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PokemonTO {
    private Long codigo;

    @NotBlank(message = "O nome do Pokémon é obrigatório")
    @Size(max = 100)
    private String nome;

    @NotBlank(message = "O tipo do Pokémon é obrigatório")
    @Size(max = 50)
    private String tipo;

    @NotNull(message = "O nível é obrigatório")
    @Min(value = 1, message = "O nível mínimo é 1")
    @Max(value = 100, message = "O nível máximo é 100")
    private Integer nivel;

    @NotNull(message = "A data de captura é obrigatória")
    @Past(message = "A data de captura deve estar no passado")
    private LocalDate dataCaptura;

    public PokemonTO() {}

    public PokemonTO(Long codigo, String nome, String tipo, Integer nivel, LocalDate dataCaptura) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
        this.dataCaptura = dataCaptura;
    }

    public Long getCodigo() { return codigo; }
    public void setCodigo(Long codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }
    public LocalDate getDataCaptura() { return dataCaptura; }
    public void setDataCaptura(LocalDate dataCaptura) { this.dataCaptura = dataCaptura; }
}