package fr.univavignon.pokedex.api;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class PokemonFactoryTest {

    private PokemonFactory pokemonFactory;

    @BeforeEach
    void setUp() {
        // Mock du générateur de nombres aléatoires
        Random mockRandom = Mockito.mock(Random.class);
        Mockito.when(mockRandom.nextInt(16)).thenReturn(5);  // Simuler un retour fixe de 5 pour les IVs

        pokemonFactory = new PokemonFactory(mockRandom);
    }

    @Test
    void testCreatePokemon() {
        int index = 1;
        int cp = 613;
        int hp = 64;
        int dust = 4000;
        int candy = 4;

        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(createdPokemon);
        assertEquals(index, createdPokemon.getIndex());
        assertEquals("NomPokemon", createdPokemon.getName());
        assertEquals(cp, createdPokemon.getCp());
        assertEquals(hp, createdPokemon.getHp());
        assertEquals(dust, createdPokemon.getDust());
        assertEquals(candy, createdPokemon.getCandy());

        // Vérifier les IVs simulés
        assertEquals(5, createdPokemon.getAttack());
        assertEquals(5, createdPokemon.getDefense());
        assertEquals(5, createdPokemon.getStamina());
        assertTrue(createdPokemon.getIv() >= 0.0 && createdPokemon.getIv() <= 100.0);
    }
}
