package br.com.fiap.pokemonapi.dao;

import br.com.fiap.pokemonapi.to.PokemonTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PokemonDAO {

    public List<PokemonTO> findAll() {
        List<PokemonTO> pokemons = new ArrayList<>();
        String sql = "SELECT codigo, nome, tipo, nivel, data_captura FROM ddd_pokemon ORDER BY codigo";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) pokemons.add(map(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pokemons;
    }

    public PokemonTO findByCodigo(Long codigo) {
        String sql = "SELECT codigo, nome, tipo, nivel, data_captura FROM ddd_pokemon WHERE codigo = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PokemonTO save(PokemonTO pokemon) {
        String sql = "INSERT INTO ddd_pokemon (nome, tipo, nivel, data_captura) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"CODIGO"})) {
            ps.setString(1, pokemon.getNome());
            ps.setString(2, pokemon.getTipo());
            ps.setInt(3, pokemon.getNivel());
            ps.setDate(4, Date.valueOf(pokemon.getDataCaptura()));
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) pokemon.setCodigo(rs.getLong(1));
            }
            return pokemon;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PokemonTO update(Long codigo, PokemonTO pokemon) {
        String sql = "UPDATE ddd_pokemon SET nome = ?, tipo = ?, nivel = ?, data_captura = ? WHERE codigo = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pokemon.getNome());
            ps.setString(2, pokemon.getTipo());
            ps.setInt(3, pokemon.getNivel());
            ps.setDate(4, Date.valueOf(pokemon.getDataCaptura()));
            ps.setLong(5, codigo);

            if (ps.executeUpdate() > 0) {
                pokemon.setCodigo(codigo);
                return pokemon;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(Long codigo) {
        String sql = "DELETE FROM ddd_pokemon WHERE codigo = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private PokemonTO map(ResultSet rs) throws SQLException {
        return new PokemonTO(
            rs.getLong("codigo"),
            rs.getString("nome"),
            rs.getString("tipo"),
            rs.getInt("nivel"),
            rs.getDate("data_captura").toLocalDate()
        );
    }
}