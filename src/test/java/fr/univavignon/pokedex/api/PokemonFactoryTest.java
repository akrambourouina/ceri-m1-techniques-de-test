package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PokemonFactoryTest {

    private PokemonFactory pokemonFactory;

    @BeforeEach
    void setUp() {
        pokemonFactory = new PokemonFactory();
    }

    @Test
    void testCreatePokemon() {
        Pokemon createdPokemon = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);

        assertNotNull(createdPokemon);
        assertEquals(0, createdPokemon.getIndex());
        assertEquals("NomPokemon", createdPokemon.getName());
        assertEquals(4, createdPokemon.getCp());
        assertEquals(64, createdPokemon.getHp());
        assertEquals(4000, createdPokemon.getDust());
        assertEquals(4, createdPokemon.getCandy());

        // Les IVs sont aléatoires, donc nous vérifions qu'ils sont dans la plage attendue
        assertTrue(createdPokemon.getAttack() >= 0 && createdPokemon.getAttack() <= 15);
        assertTrue(createdPokemon.getDefense() >= 0 && createdPokemon.getDefense() <= 15);
        assertTrue(createdPokemon.getStamina() >= 0 && createdPokemon.getStamina() <= 15);
        assertTrue(createdPokemon.getIv() >= 0.0 && createdPokemon.getIv() <= 100.0);
    }
}
