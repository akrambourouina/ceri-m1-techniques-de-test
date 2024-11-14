package fr.univavignon.pokedex.api;

/**
 * Implementation of the IPokemonTrainerFactory interface.
 */
public class PokemonTrainerFactory implements IPokemonTrainerFactory {

    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        // Crée un Pokedex en utilisant le pokedexFactory avec des fournisseurs de métadonnées et de Pokémon
        IPokedex pokedex = pokedexFactory.createPokedex(new PokemonMetadataProvider(), new PokemonFactory());

        // Crée et retourne l'instance de PokemonTrainer
        return new PokemonTrainer(name, team, pokedex);
    }
}
