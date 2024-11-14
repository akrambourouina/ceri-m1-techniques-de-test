package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PokemonTrainerFactoryTest {

    private IPokedexFactory pokedexFactory;
    private PokemonTrainerFactory pokemonTrainerFactory;

    @BeforeEach
    void setUp() {
        // Initialisation de mocks pour les dépendances
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        pokemonTrainerFactory = new PokemonTrainerFactory();
    }

    @Test
    void testCreateTrainer() {
        // Nom et équipe du dresseur à tester
        String name = "Ash";
        Team team = Team.INSTINCT;

        // Création d'un mock de IPokedex pour vérifier son association avec le dresseur
        IPokedex pokedex = Mockito.mock(IPokedex.class);
        // Configuration du mock pour retourner le pokedex simulé lorsqu'il est demandé
        when(pokedexFactory.createPokedex(any(PokemonMetadataProvider.class), any(PokemonFactory.class))).thenReturn(pokedex);

        // Appel de la méthode createTrainer et vérification des résultats
        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer(name, team, pokedexFactory);

        // Vérifie que l'objet trainer n'est pas null
        assertNotNull(trainer);
        // Vérifie les attributs de l'objet trainer
        assertEquals(name, trainer.getName());
        assertEquals(team, trainer.getTeam());
        assertEquals(pokedex, trainer.getPokedex());

        // Vérifie que createPokedex a été appelé une fois avec les bons arguments
        verify(pokedexFactory, times(1)).createPokedex(any(PokemonMetadataProvider.class), any(PokemonFactory.class));
    }
}
