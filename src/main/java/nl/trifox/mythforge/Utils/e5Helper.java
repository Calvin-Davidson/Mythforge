package nl.trifox.mythforge.Utils;

public final class e5Helper {

    public static int proficiencyBonus(int level) {
        if (level >= 17) return 6;
        if (level >= 13) return 5;
        if (level >= 9)  return 4;
        if (level >= 5)  return 3;
        return 2;
    }

    public static int abilityMod(int score) {
        return Math.floorDiv(score - 10, 2);
    }
}
