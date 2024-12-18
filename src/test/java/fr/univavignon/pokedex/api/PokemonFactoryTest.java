package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokemonFactoryTest {

    private IPokemonMetadataProvider mockMetadataProvider;
    private PokemonFactory pokemonFactory;

    @BeforeEach
    public void setUp() {
        // Création d'un mock pour le fournisseur de métadonnées
        mockMetadataProvider = mock(IPokemonMetadataProvider.class);

        // Initialisation de la fabrique avec le mock
        pokemonFactory = new PokemonFactory();
    }

    @Test
    public void testCreatePokemon() throws PokedexException {
        // Définir un Pokémon fictif pour l'index 0 (par exemple, Bulbasaur)
        PokemonMetadata mockMetadata = new PokemonMetadata(0, "Bulbasaur", 126, 126, 90);
        when(mockMetadataProvider.getPokemonMetadata(0)).thenReturn(mockMetadata);


        // Appeler la méthode createPokemon pour obtenir un Pokémon
        Pokemon pokemon = pokemonFactory.createPokemon(0, 150, 100, 200, 25);

        // Vérifier que le Pokémon a été correctement créé
        assertNotNull(pokemon);
        assertEquals(0, pokemon.getIndex());
        assertEquals("Bulbasaur", pokemon.getName());
        assertEquals(126, pokemon.getAttack());
        assertEquals(126, pokemon.getDefense());
        assertEquals(90, pokemon.getStamina());
        assertEquals(150, pokemon.getCp());
        assertEquals(100, pokemon.getHp());
        assertEquals(200, pokemon.getDust());
        assertEquals(25, pokemon.getCandy());

        // Vérifier que l'IV a été calculé correctement (à partir de l'exemple de formule)
        double expectedIV = (126 + 126 + 90) / 45.0;
        assertEquals(expectedIV, pokemon.getIv(), 0.01);
    }

    @Test
    public void testCreatePokemonWithInvalidIndex() throws PokedexException {
        // Configurer un index qui ne correspond à aucun Pokémon
        when(mockMetadataProvider.getPokemonMetadata(999)).thenThrow(new PokedexException("Invalid Pokemon index"));

        // Tester la gestion des erreurs pour un index invalide
        Pokemon pokemon = pokemonFactory.createPokemon(999, 150, 100, 200, 25);

        // Vérifier que le Pokémon n'a pas été créé (renvoi null en cas d'erreur)
        assertNull(pokemon);
    }
}
