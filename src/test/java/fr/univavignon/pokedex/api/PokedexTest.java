package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PokedexTest {

    private Pokedex pokedex;
    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;
    private Pokemon testPokemon;

    @BeforeEach
    void setUp() {
        metadataProviderMock = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = Mockito.mock(IPokemonFactory.class);
        pokedex = new Pokedex(metadataProviderMock, pokemonFactoryMock);

        testPokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
    }

    @Test
    void testSize() {
        assertEquals(0, pokedex.size());
        pokedex.addPokemon(testPokemon);
        assertEquals(1, pokedex.size());
    }

    @Test
    void testAddPokemon() {
        int index = pokedex.addPokemon(testPokemon);
        assertEquals(0, index); // Index du premier Pokémon
        try {
            assertEquals(testPokemon, pokedex.getPokemon(index));
        } catch (PokedexException e) {
            fail("PokedexException was thrown with a valid index: " + e.getMessage());
        }

    }

    @Test
    void testGetPokemonValid() throws PokedexException {
        pokedex.addPokemon(testPokemon);
        Pokemon retrievedPokemon = pokedex.getPokemon(0);
        assertEquals(testPokemon, retrievedPokemon);
    }

    @Test
    void testGetPokemonInvalid() {
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(1));
    }

    @Test
    void testGetPokemons() {
        pokedex.addPokemon(testPokemon);
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertEquals(1, pokemons.size());
        assertEquals(testPokemon, pokemons.get(0));
        assertThrows(UnsupportedOperationException.class, () -> pokemons.add(testPokemon)); // Vérifie que la liste est immuable
    }

    @Test
    void testGetPokemonsSorted() {
        Pokemon charmander = new Pokemon(1, "Salamèche", 94, 94, 78, 500, 50, 2000, 3, 60.0);
        pokedex.addPokemon(testPokemon);
        pokedex.addPokemon(charmander);

        List<Pokemon> sortedPokemons = pokedex.getPokemons(Comparator.comparing(Pokemon::getName));
        assertEquals("Bulbizarre", sortedPokemons.get(0).getName());
        assertEquals("Salamèche", sortedPokemons.get(1).getName());
    }

    @Test
    void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata metadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        when(metadataProviderMock.getPokemonMetadata(0)).thenReturn(metadata);

        PokemonMetadata retrievedMetadata = pokedex.getPokemonMetadata(0);
        assertEquals(metadata, retrievedMetadata);
        verify(metadataProviderMock).getPokemonMetadata(0); // Vérifie l'appel à metadataProvider
    }

    @Test
    void testCreatePokemon() {
        when(pokemonFactoryMock.createPokemon(0, 613, 64, 4000, 4)).thenReturn(testPokemon);

        Pokemon createdPokemon = pokedex.createPokemon(0, 613, 64, 4000, 4);
        assertEquals(testPokemon, createdPokemon);
        verify(pokemonFactoryMock).createPokemon(0, 613, 64, 4000, 4); // Vérifie l'appel à pokemonFactory
    }
}
