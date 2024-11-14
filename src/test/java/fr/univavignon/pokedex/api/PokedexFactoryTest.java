package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PokedexFactoryTest {

    private PokedexFactory pokedexFactory;
    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;

    @BeforeEach
    void setUp() {
        // Création des mocks pour les dépendances
        metadataProviderMock = mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = mock(IPokemonFactory.class);

        // Initialisation de l'instance de PokedexFactory
        pokedexFactory = new PokedexFactory();
    }

    @Test
    void testCreatePokedex() {
        // Appel à la méthode de création de Pokedex
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Vérification que le Pokedex créé n'est pas nul
        assertNotNull(pokedex, "Le Pokedex ne doit pas être nul.");

        // Vérification que les dépendances ont été injectées correctement
        assertTrue(pokedex instanceof Pokedex, "Le Pokedex doit être une instance de la classe Pokedex.");

        // Vérification des mocks
        Pokedex realPokedex = (Pokedex) pokedex;
        assertEquals(metadataProviderMock, realPokedex.getMetadataProvider(), "Le provider de métadonnées ne correspond pas.");
        assertEquals(pokemonFactoryMock, realPokedex.getPokemonFactory(), "Le factory de Pokémon ne correspond pas.");
    }
}
