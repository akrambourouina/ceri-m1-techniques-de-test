package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PokemonComparatorsTest {

    private Pokemon pokemon1;
    private Pokemon pokemon2;

    @BeforeEach
    void setUp() {
        // Initialisation de deux Pokémon avec des valeurs différentes pour les tests
        pokemon1 = new Pokemon(1, "Bulbasaur", 500, 60, 3000, 10, 10, 10, 10, 75.0);
        pokemon2 = new Pokemon(2, "Ivysaur", 600, 70, 4000, 20, 12, 13, 12, 80.0);
    }

    @Test
    void testCompareByName() {
        // Comparaison par nom (Bulbasaur < Ivysaur)
        int result = PokemonComparators.NAME.compare(pokemon1, pokemon2);
        assertTrue(result < 0, "Bulbasaur should come before Ivysaur when sorted by name");
    }

    @Test
    void testCompareByIndex() {
        // Comparaison par index (1 < 2)
        int result = PokemonComparators.INDEX.compare(pokemon1, pokemon2);
        assertTrue(result < 0, "Pokemon with index 1 should come before index 2");
    }

    @Test
    void testCompareByCp() {
        // Comparaison par CP (500 < 600)
        int result = PokemonComparators.CP.compare(pokemon1, pokemon2);
        assertTrue(result < 0, "Pokemon with CP 500 should come before CP 600");
    }

    @Test
    void testCompareByNameEqual() {
        // Test de la comparaison de Pokémon ayant le même nom
        Pokemon pokemon3 = new Pokemon(3, "Bulbasaur", 450, 60, 3000, 10, 10, 10, 10, 70.0);

        // Même nom, on doit avoir un résultat égal
        int result = PokemonComparators.NAME.compare(pokemon1, pokemon3);
        assertEquals(0, result, "Pokemons with the same name should be considered equal when sorted by name");
    }

    @Test
    void testCompareByIndexEqual() {
        // Test de la comparaison de Pokémon ayant le même index
        Pokemon pokemon3 = new Pokemon(1, "Charmander", 450, 50, 2000, 15, 8, 9, 8, 70.0);

        // Même index, on doit avoir un résultat égal
        int result = PokemonComparators.INDEX.compare(pokemon1, pokemon3);
        assertEquals(0, result, "Pokemons with the same index should be considered equal when sorted by index");
    }

    @Test
    void testCompareByCpEqual() {
        // Test de la comparaison de Pokémon ayant le même CP
        Pokemon pokemon3 = new Pokemon(4, "Squirtle", 500, 50, 2500, 12, 10, 11, 9, 70.0);

        // Même CP, on doit avoir un résultat égal
        int result = PokemonComparators.CP.compare(pokemon1, pokemon3);
        assertEquals(0, result, "Pokemons with the same CP should be considered equal when sorted by CP");
    }

    @Test
    void testSortPokemonsByName() {
        List<Pokemon> pokemons = List.of(pokemon2, pokemon1);

        // Tri des Pokémon par nom
        pokemons.sort(PokemonComparators.NAME);

        // Vérification que le tri a bien fonctionné
        assertEquals("Bulbasaur", pokemons.get(0).getName(), "Bulbasaur should be first when sorted by name");
        assertEquals("Ivysaur", pokemons.get(1).getName(), "Ivysaur should be second when sorted by name");
    }

    @Test
    void testSortPokemonsByCp() {
        List<Pokemon> pokemons = List.of(pokemon2, pokemon1);

        // Tri des Pokémon par CP
        pokemons.sort(PokemonComparators.CP);

        // Vérification que le tri a bien fonctionné
        assertEquals(500, pokemons.get(0).getCp(), "Pokémon with CP 500 should be first when sorted by CP");
        assertEquals(600, pokemons.get(1).getCp(), "Pokémon with CP 600 should be second when sorted by CP");
    }
}
