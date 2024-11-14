package fr.univavignon.pokedex.api;

/**
 * Implementation of the IPokemonFactory interface.
 */
public class PokemonFactory implements IPokemonFactory {

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        // Logique de calcul pour les IVs
        int attack = (int) (Math.random() * 15);
        int defense = (int) (Math.random() * 15);
        int stamina = (int) (Math.random() * 15);

        // Calcul du pourcentage des IVs
        double ivPercentage = (attack + defense + stamina) / 45.0 * 100;

        // Création de l'objet Pokemon
        return new Pokemon(index, "NomPokemon", cp, hp, dust, candy, attack, defense, stamina, ivPercentage);
    }
}
