package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class test {

	private IPokemonFactory pokemonFactory;
	private PokemonMetadataProvider mockMetadataProvider;

	@BeforeEach
	public void setUp() {
		pokemonFactory = new PokemonFactory();
		mockMetadataProvider = mock(PokemonMetadataProvider.class);
	}

	@Test
	public void testCreatePokemonValid() throws PokedexException {
		// Préparer des métadonnées fictives pour un Pokémon (par exemple, Bulbasaur)
		PokemonMetadata mockMetadata = new PokemonMetadata(1, "Bulbasaur", 126, 126, 90);
		when(mockMetadataProvider.getPokemonMetadata(1)).thenReturn(mockMetadata);

		// Créer le Pokémon avec des paramètres valides
		Pokemon pokemon = pokemonFactory.createPokemon(1, 150, 100, 200, 25);

		// Vérifier que le Pokémon a bien été créé
		assertNotNull(pokemon);
		assertEquals("Bulbasaur", pokemon.getName());
		assertEquals(150, pokemon.getCp());
		assertEquals(100, pokemon.getHp());
		assertEquals(200, pokemon.getDust());
		assertEquals(25, pokemon.getCandy());
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

	@Test
	public void testCreatePokemonIvCalculation() throws PokedexException {
		// Préparer des métadonnées fictives pour un Pokémon (par exemple, Bulbasaur)
		PokemonMetadata mockMetadata = new PokemonMetadata(1, "Bulbasaur", 126, 126, 90);
		when(mockMetadataProvider.getPokemonMetadata(1)).thenReturn(mockMetadata);

		// Créer le Pokémon
		Pokemon pokemon = pokemonFactory.createPokemon(1, 150, 100, 200, 25);

		// Calculer l'IV attendu
		double expectedIv = (126 + 126 + 90) / 45.0;

		// Vérifier que l'IV est calculé correctement
		assertEquals(expectedIv, pokemon.getIv(), 0.01);
	}

	@Test
	public void testCreatePokemonWithMissingMetadata() throws PokedexException {
		// Utiliser un index invalide pour simuler le Pokémon "MISSINGNO"
		Pokemon pokemon = pokemonFactory.createPokemon(0, 150, 100, 200, 25);

		// Vérifier que le nom du Pokémon est "MISSINGNO"
		assertNotNull(pokemon);
		assertEquals("MISSINGNO", pokemon.getName());
	}
}
