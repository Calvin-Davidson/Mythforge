package nl.trifox.mythforge.Consts;

import nl.trifox.mythforge.Characters.CharacterData;

public enum CharacterField {
    STRENGTH("strength") {
        @Override
        public void apply(CharacterData c, String v) {
            c.setStrength(parseRangedInt(v, 1, 30, "Strength"));
        }

        @Override
        public String readableValue(CharacterData c) {
            return Integer.toString(c.getStrength());
        }
    },
    DEXTERITY("dexterity") {
        @Override
        public void apply(CharacterData c, String v) {
            c.setDexterity(parseRangedInt(v, 1, 30, "Dexterity"));
        }

        @Override
        public String readableValue(CharacterData c) {
            return Integer.toString(c.getDexterity());
        }
    },
    CONSTITUTION("constitution") {
        @Override
        public void apply(CharacterData c, String v) {
            c.setConstitution(parseRangedInt(v, 1, 30, "Constitution"));
        }

        @Override
        public String readableValue(CharacterData c) {
            return Integer.toString(c.getConstitution());
        }
    },
    INTELLIGENCE("intelligence") {
        @Override
        public void apply(CharacterData c, String v) {
            c.setIntelligence(parseRangedInt(v, 1, 30, "Intelligence"));
        }

        @Override
        public String readableValue(CharacterData c) {
            return Integer.toString(c.getIntelligence());
        }
    },
    WISDOM("wisdom") {
        @Override
        public void apply(CharacterData c, String v) {
            c.setWisdom(parseRangedInt(v, 1, 30, "Wisdom"));
        }

        @Override
        public String readableValue(CharacterData c) {
            return Integer.toString(c.getWisdom());
        }
    },
    CHARISMA("charisma") {
        @Override
        public void apply(CharacterData c, String v) {
            c.setCharisma(parseRangedInt(v, 1, 30, "Charisma"));
        }

        @Override
        public String readableValue(CharacterData c) {
            return Integer.toString(c.getCharisma());
        }
    },

    MAX_HP("max HP") {
        @Override
        public void apply(CharacterData c, String v) {
            int max = parseRangedInt(v, 1, 9999, "Max HP");
            c.setMaxHealth(max);
            if (c.getCurrentHealth() > max) c.setCurrentHealth(max);
        }

        @Override
        public String readableValue(CharacterData c) {
            return c.getCurrentHealth() + "/" + c.getMaxHealth();
        }
    },
    CURRENT_HP("current HP") {
        @Override
        public void apply(CharacterData c, String v) {
            int cur = parseRangedInt(v, 0, 9999, "Current HP");
            int max = c.getMaxHealth();
            if (max > 0 && cur > max)
                throw new IllegalArgumentException("Current HP cannot exceed Max HP (" + max + ").");
            c.setCurrentHealth(cur);
        }

        @Override
        public String readableValue(CharacterData c) {
            return c.getCurrentHealth() + "/" + c.getMaxHealth();
        }
    },

    ARMOR_CLASS("AC") {
        @Override
        public void apply(CharacterData c, String v) {
            c.setArmorClass(parseRangedInt(v, 1, 99, "Armor Class"));
        }

        @Override
        public String readableValue(CharacterData c) {
            return Integer.toString(c.getArmorClass());
        }
    },
    INITIATIVE("initiative") {
        @Override
        public void apply(CharacterData c, String v) {
            c.setInitiativeBonus(parseRangedInt(v, -50, 50, "Initiative bonus"));
        }

        @Override
        public String readableValue(CharacterData c) {
            return Integer.toString(c.getInitiativeBonus());
        }
    },
    SPEED("speed") {
        @Override
        public void apply(CharacterData c, String v) {
            c.setSpeed(parseRangedInt(v, 0, 999, "Speed"));
        }

        @Override
        public String readableValue(CharacterData c) {
            return Integer.toString(c.getSpeed());
        }
    },

    RACE("race") {
        @Override
        public void apply(CharacterData c, String v) {
            if (v.isBlank()) throw new IllegalArgumentException("Race cannot be empty.");
            c.setRace(v);
        }

        @Override
        public String readableValue(CharacterData c) {
            return String.valueOf(c.getRace());
        }
    },
    CLASS("class") {
        @Override
        public void apply(CharacterData c, String v) {
            if (v.isBlank()) throw new IllegalArgumentException("Class cannot be empty.");
            c.setCharacterClass(v);
        }

        @Override
        public String readableValue(CharacterData c) {
            return String.valueOf(c.getCharacterClass());
        }
    },
    LEVEL("level") {
        @Override
        public void apply(CharacterData c, String v) {
            c.setLevel(parseRangedInt(v, 1, 20, "Level"));
        }

        @Override
        public String readableValue(CharacterData c) {
            return Integer.toString(c.getLevel());
        }
    };

    public final String displayName;

    CharacterField(String displayName) {
        this.displayName = displayName;
    }

    public abstract void apply(CharacterData c, String v);

    public abstract String readableValue(CharacterData c);

    private static int parseRangedInt(String v, int min, int max, String label) {
        final int n;
        try {
            n = Integer.parseInt(v);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(label + " must be a number.");
        }
        if (n < min || n > max) {
            throw new IllegalArgumentException(label + " must be between " + min + " and " + max + ".");
        }
        return n;
    }
}
