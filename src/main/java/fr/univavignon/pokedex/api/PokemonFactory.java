package fr.univavignon.pokedex.api;

/**
 * Implementation of the IPokemonFactory interface.
 *
 * @author fv
 */
public class PokemonFactory implements IPokemonFactory {

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        try {

            PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
            // Retrieve metadata for the given Pokemon index
            PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);

            // Calculate IV (this is just an example, the actual calculation might depend on other factors)
            double iv = ((metadata.getAttack()+metadata.getDefense()+metadata.getStamina())/45.0);

            // Create and return the Pokemon instance with the computed IV
            return new Pokemon(
                    index,
                    metadata.getName(),
                    metadata.getAttack(),
                    metadata.getDefense(),
                    metadata.getStamina(),
                    cp,
                    hp,
                    dust,
                    candy,
                    iv
            );
        } catch (PokedexException e) {
            // Handle error if the metadata for the given index is not found
            System.err.println("Error creating Pokemon: " + e.getMessage());
            return null;
        }
    }


}
