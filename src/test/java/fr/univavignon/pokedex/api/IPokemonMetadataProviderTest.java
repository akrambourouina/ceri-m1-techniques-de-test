package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider metadataProvider;

    @BeforeEach
    void setUp() {
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
    }

    @Test
    void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata metadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        Mockito.when(metadataProvider.getPokemonMetadata(0)).thenReturn(metadata);

        PokemonMetadata result = metadataProvider.getPokemonMetadata(0);
        assertNotNull(result);
        assertEquals("Bulbizarre", result.getName());
        assertEquals(126, result.getAttack());
        assertEquals(126, result.getDefense());
        assertEquals(90, result.getStamina());
    }

    @Test
    void testGetPokemonMetadataInvalidIndex() throws PokedexException {
        Mockito.when(metadataProvider.getPokemonMetadata(151)).thenThrow(new PokedexException("Invalid index"));

        assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(151);
        });
    }
}
