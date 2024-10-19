package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IPokedexTest {

    private IPokedex pokedex;
    private Pokemon pokemon;

    @BeforeEach
    void setUp() {
        pokedex = Mockito.mock(IPokedex.class);
        pokemon = new Pokemon(0, "Bulbizarre",126, 126, 90, 613, 64, 4000, 4, 0.56);
    }

    @Test
    void testAddPokemon() {
        Mockito.when(pokedex.addPokemon(pokemon)).thenReturn(1);

        int index = pokedex.addPokemon(pokemon);
        assertEquals(1, index);
    }

    @Test
    void testGetPokemon() throws PokedexException {
        Mockito.when(pokedex.getPokemon(0)).thenReturn(pokemon);

        Pokemon result = pokedex.getPokemon(0);
        assertNotNull(result);
        assertEquals("Bulbizarre", result.getName());
        assertEquals(126, result.getAttack());
        assertEquals(126, result.getDefense());
        assertEquals(90, result.getStamina());
        assertEquals(613, result.getCp());
        assertEquals(64, result.getHp());
        assertEquals(4000, result.getDust());
        assertEquals(4, result.getCandy());
        assertEquals(0.56, result.getIv());

    }

    @Test
    void testGetPokemons() {
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(pokemon);
        Mockito.when(pokedex.getPokemons()).thenReturn(pokemonList);

        List<Pokemon> result = pokedex.getPokemons();
        assertEquals(1, result.size());
        assertEquals(pokemon, result.get(0));
    }
}
