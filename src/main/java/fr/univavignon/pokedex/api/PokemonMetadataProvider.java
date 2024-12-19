package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the IPokemonMetadataProvider interface.
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private final Map<Integer, PokemonMetadata> metadataDatabase;

    public PokemonMetadataProvider() {
        metadataDatabase = new HashMap<>();
        // Initialisation des métadonnées fictives pour les Pokémon
        metadataDatabase.put(1, new PokemonMetadata(1, "Bulbasaur", 126, 126, 90));
        metadataDatabase.put(0, new PokemonMetadata(0, "Ivysaur", 156, 158, 120));
        metadataDatabase.put(2, new PokemonMetadata(2, "Venusaur", 198, 200, 160));
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        PokemonMetadata metadata = metadataDatabase.get(index);
        if (metadata == null) {
            throw new PokedexException("Invalid Pokemon index: " + index);
        }
        return metadata;
    }
}
