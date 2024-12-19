package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PokemonMetadataProvider.
 */
class PokemonMetadataProviderTest {

    private PokemonMetadataProvider metadataProvider;


    @BeforeEach
    void setUp() {
        // Initialisation avant chaque test
        metadataProvider = new PokemonMetadataProvider();
    }

    @Test
    void testGetPokemonMetadata_ValidIndex() throws PokedexException {
        // Test pour récupérer les métadonnées d'un Pokémon valide
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(1); // Bulbasaur

        assertNotNull(metadata, "Metadata should not be null");
        assertEquals(1, metadata.getIndex(), "Index should be 0");
        assertEquals("Bulbasaur", metadata.getName(), "Name should be Bulbasaur");
        assertEquals(126, metadata.getAttack(), "Attack should be 126");
        assertEquals(126, metadata.getDefense(), "Defense should be 126");
        assertEquals(90, metadata.getStamina(), "Stamina should be 90");
    }

    @Test
    void testGetPokemonMetadata_InvalidIndex() {
        // Test pour récupérer les métadonnées d'un Pokémon avec un index invalide
        PokedexException exception = assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(999); // Index qui n'existe pas
        });

        assertEquals("Invalid Pokemon index: 999", exception.getMessage(), "Exception message should be correct");
    }

    @Test
    void testGetPokemonMetadata_ValidIndexIvysaur() throws PokedexException {
        // Test pour récupérer les métadonnées d'un autre Pokémon valide
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(0); // Ivysaur

        assertNotNull(metadata, "Metadata should not be null");
        assertEquals(0, metadata.getIndex(), "Index should be 1");
        assertEquals("Ivysaur", metadata.getName(), "Name should be Ivysaur");
        assertEquals(156, metadata.getAttack(), "Attack should be 156");
        assertEquals(158, metadata.getDefense(), "Defense should be 158");
        assertEquals(120, metadata.getStamina(), "Stamina should be 120");
    }

    @Test
    void testGetPokemonMetadata_ValidIndexVenusaur() throws PokedexException {
        // Test pour récupérer les métadonnées de Venusaur
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(2); // Venusaur

        assertNotNull(metadata, "Metadata should not be null");
        assertEquals(2, metadata.getIndex(), "Index should be 2");
        assertEquals("Venusaur", metadata.getName(), "Name should be Venusaur");
        assertEquals(198, metadata.getAttack(), "Attack should be 198");
        assertEquals(200, metadata.getDefense(), "Defense should be 200");
        assertEquals(160, metadata.getStamina(), "Stamina should be 160");
    }
}
