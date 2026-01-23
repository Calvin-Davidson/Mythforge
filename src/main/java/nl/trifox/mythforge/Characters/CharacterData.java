package nl.trifox.mythforge.Characters;

import java.util.UUID;

public class CharacterData {
    private final String Name;
    private final UUID uuid;
    private boolean isActive;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private int maxHealth;
    private int currentHealth;
    private int armorClass;

    private int initiativeBonus;
    private int speed;

    private String race;
    private String characterClass;
    private int level;

    public CharacterData(UUID uuid, String name) {
        this.Name = name;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return this.Name;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getConstitution() { return constitution; }
    public void setConstitution(int constitution) { this.constitution = constitution; }

    public int getIntelligence() { return intelligence; }
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }

    public int getWisdom() { return wisdom; }
    public void setWisdom(int wisdom) { this.wisdom = wisdom; }

    public int getCharisma() { return charisma; }
    public void setCharisma(int charisma) { this.charisma = charisma; }

    public int getInitiativeBonus() { return initiativeBonus; }
    public void setInitiativeBonus(int initiativeBonus) { this.initiativeBonus = initiativeBonus; }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
}
