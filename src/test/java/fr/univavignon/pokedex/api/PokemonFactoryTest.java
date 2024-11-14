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
        int index = 1; // Utilisation de l'index 1 pour tester
        int cp = 613;   // CP attendu
        int hp = 64;
        int dust = 4000;
        int candy = 4;

        // Crée un Pokémon avec un index spécifique
        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(createdPokemon);
        assertEquals(index, createdPokemon.getIndex()); // Vérifie que l'index est correctement assigné
        assertEquals("NomPokemon", createdPokemon.getName()); // Nom générique
        assertEquals(cp, createdPokemon.getCp()); // Vérifie que le CP est bien 613
        assertEquals(hp, createdPokemon.getHp());
        assertEquals(dust, createdPokemon.getDust());
        assertEquals(candy, createdPokemon.getCandy());

        // Les IVs sont aléatoires, donc nous vérifions qu'ils sont dans la plage attendue
        assertTrue(createdPokemon.getAttack() >= 0 && createdPokemon.getAttack() <= 15);
        assertTrue(createdPokemon.getDefense() >= 0 && createdPokemon.getDefense() <= 15);
        assertTrue(createdPokemon.getStamina() >= 0 && createdPokemon.getStamina() <= 15);
        assertTrue(createdPokemon.getIv() >= 0.0 && createdPokemon.getIv() <= 100.0);
    }
}
