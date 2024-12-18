package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokemonFactoryTest {

    private IPokemonMetadataProvider mockMetadataProvider;
    private RocketPokemonFactory pokemonFactory;

    @BeforeEach
    public void setUp() {
        // Création d'un mock pour le fournisseur de métadonnées
        mockMetadataProvider = mock(IPokemonMetadataProvider.class);

        // Initialisation de la fabrique avec le mock
        pokemonFactory = new RocketPokemonFactory();
    }

    @Test
    public void testCreatePokemonName() throws PokedexException {
        // Définir un Pokémon fictif pour l'index 1 (Bulbasaur)
        PokemonMetadata mockMetadata = new PokemonMetadata(1, "Bulbasaur", 126, 126, 90);
        when(mockMetadataProvider.getPokemonMetadata(1)).thenReturn(mockMetadata);

        // Appeler la méthode createPokemon pour obtenir un Pokémon
        Pokemon pokemon = pokemonFactory.createPokemon(1, 150, 100, 200, 25);

        // Vérifier que le nom du Pokémon est correct
        assertEquals("Bulbasaur", pokemon.getName());
    }

    @Test
    public void testCreatePokemonCp() throws PokedexException {
        // Définir un Pokémon fictif pour l'index 1 (Bulbasaur)
        PokemonMetadata mockMetadata = new PokemonMetadata(1, "Bulbasaur", 126, 126, 90);
        when(mockMetadataProvider.getPokemonMetadata(1)).thenReturn(mockMetadata);

        // Appeler la méthode createPokemon pour obtenir un Pokémon
        Pokemon pokemon = pokemonFactory.createPokemon(1, 150, 100, 200, 25);

        // Vérifier que le CP est correctement assigné
        assertEquals(150, pokemon.getCp());
    }

    @Test
    public void testCreatePokemonHp() throws PokedexException {
        // Définir un Pokémon fictif pour l'index 1 (Bulbasaur)
        PokemonMetadata mockMetadata = new PokemonMetadata(1, "Bulbasaur", 126, 126, 90);
        when(mockMetadataProvider.getPokemonMetadata(1)).thenReturn(mockMetadata);

        // Appeler la méthode createPokemon pour obtenir un Pokémon
        Pokemon pokemon = pokemonFactory.createPokemon(1, 150, 100, 200, 25);

        // Vérifier que les HP sont correctement assignés
        assertEquals(100, pokemon.getHp());
    }

    @Test
    public void testCreatePokemonDust() throws PokedexException {
        // Définir un Pokémon fictif pour l'index 1 (Bulbasaur)
        PokemonMetadata mockMetadata = new PokemonMetadata(1, "Bulbasaur", 126, 126, 90);
        when(mockMetadataProvider.getPokemonMetadata(1)).thenReturn(mockMetadata);

        // Appeler la méthode createPokemon pour obtenir un Pokémon
        Pokemon pokemon = pokemonFactory.createPokemon(1, 150, 100, 200, 25);

        // Vérifier que le Dust est correctement assigné
        assertEquals(200, pokemon.getDust());
    }

    @Test
    public void testCreatePokemonCandy() throws PokedexException {
        // Définir un Pokémon fictif pour l'index 1 (Bulbasaur)
        PokemonMetadata mockMetadata = new PokemonMetadata(1, "Bulbasaur", 126, 126, 90);
        when(mockMetadataProvider.getPokemonMetadata(1)).thenReturn(mockMetadata);

        // Appeler la méthode createPokemon pour obtenir un Pokémon
        Pokemon pokemon = pokemonFactory.createPokemon(1, 150, 100, 200, 25);

        // Vérifier que les Candy sont correctement assignés
        assertEquals(25, pokemon.getCandy());
    }


    @Test
    public void testCreatePokemonWithInvalidIndex() throws PokedexException {
        // Configurer un index qui ne correspond à aucun Pokémon
        when(mockMetadataProvider.getPokemonMetadata(999)).thenThrow(new PokedexException("Invalid Pokemon index"));

        // Tester la gestion des erreurs pour un index invalide
        Pokemon pokemon = pokemonFactory.createPokemon(999, 150, 100, 200, 25);

        // Vérifier que le Pokémon n'a pas été créé (renvoi MISSINGNO en cas d'erreur selon la logique de la fabrique)
        assertNotNull(pokemon);
        assertEquals("MISSINGNO", pokemon.getName()); // Attendre "MISSINGNO" au lieu de null
    }
}
