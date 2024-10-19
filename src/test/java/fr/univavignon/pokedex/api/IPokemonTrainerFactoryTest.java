package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class IPokemonTrainerFactoryTest {

    private IPokemonTrainerFactory pokemonTrainerFactory;
    private IPokedexFactory pokedexFactory;

    @BeforeEach
    void setUp() {
        pokemonTrainerFactory = Mockito.mock(IPokemonTrainerFactory.class);
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
    }

    @Test
    void testCreateTrainer() {
        PokemonTrainer trainer = new PokemonTrainer("Ash", Team.VALOR, Mockito.mock(IPokedex.class));
        Mockito.when(pokemonTrainerFactory.createTrainer("Ash", Team.VALOR, pokedexFactory)).thenReturn(trainer);

        PokemonTrainer createdTrainer = pokemonTrainerFactory.createTrainer("Ash", Team.VALOR, pokedexFactory);
        assertNotNull(createdTrainer);
        assertEquals("Ash", createdTrainer.getName());
    }
}
