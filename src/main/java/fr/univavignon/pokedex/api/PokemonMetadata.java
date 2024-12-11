package fr.univavignon.pokedex.api;

/**
 * Pokemon metadata POJO.
 *
 * @author fv
 */
public class PokemonMetadata {

    /** Pokemon index. **/
    private final int index;

    /** Pokemon name. **/
    private final String name;

    /** Pokemon attack level. **/
    private final int attack;

    /** Pokemon defense level. **/
    private final int defense;

    /** Pokemon stamina level. **/
    private final int stamina;

    /**
     * Default constructor.
     *
     * @param index Pokemon index.
     * @param name Pokemon name.
     * @param attack Attack level.
     * @param defense Defense level.
     * @param stamina Stamina level.
     */
    public PokemonMetadata(final int index, final String name, final int attack, final int defense, final int stamina) {
        this.index = index;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.stamina = stamina;
    }

    /**
     * Returns the index of the pokemon.
     *
     * @return The index of the pokemon.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the name of the pokemon.
     *
     * @return The name of the pokemon.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the attack level of the pokemon.
     *
     * @return The attack level of the pokemon.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Returns the defense level of the pokemon.
     *
     * @return The defense level of the pokemon.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Returns the stamina level of the pokemon.
     *
     * @return The stamina level of the pokemon.
     */
    public int getStamina() {
        return stamina;
    }
}
