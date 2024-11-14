package fr.univavignon.pokedex.api;

/**
 * Implementation of the IPokemonFactory interface.
 */
import java.util.Random;

public class PokemonFactory implements IPokemonFactory {

    private final Random random;

    // Constructeur pour injection du générateur de nombres aléatoires
    public PokemonFactory(Random random) {
        this.random = random;
    }

    // Si aucun générateur n'est passé, utilise un générateur par défaut
    public PokemonFactory() {
        this.random = new Random();
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        // Logique de calcul pour les IVs
        int attack = random.nextInt(16);  // Genère un nombre entre 0 et 15
        int defense = random.nextInt(16);  // Genère un nombre entre 0 et 15
        int stamina = random.nextInt(16);  // Genère un nombre entre 0 et 15

        // Calcul du pourcentage des IVs
        double ivPercentage = (attack + defense + stamina) / 45.0 * 100;

        // Crée un Pokémon avec les attributs fournis, y compris le CP
        return new Pokemon(index, "NomPokemon", cp, hp, dust, candy, attack, defense, stamina, ivPercentage);
    }
}

