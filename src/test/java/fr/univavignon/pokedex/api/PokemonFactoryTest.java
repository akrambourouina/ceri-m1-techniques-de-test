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
        assertEquals(1, createdPokemon.getIndex());
        assertEquals("Salamèche", createdPokemon.getName());
        assertEquals(500, createdPokemon.getCp());
        assertEquals(50, createdPokemon.getHp());
        assertEquals(2000, createdPokemon.getDust());
        assertEquals(3, createdPokemon.getCandy());

        // Les IVs sont aléatoires, donc nous vérifions qu'ils sont dans la plage attendue
        assertTrue(createdPokemon.getAttack() >= 0 && createdPokemon.getAttack() <= 15);
        assertTrue(createdPokemon.getDefense() >= 0 && createdPokemon.getDefense() <= 15);
        assertTrue(createdPokemon.getStamina() >= 0 && createdPokemon.getStamina() <= 15);
        assertTrue(createdPokemon.getIv() >= 0.0 && createdPokemon.getIv() <= 100.0);
    }
}
