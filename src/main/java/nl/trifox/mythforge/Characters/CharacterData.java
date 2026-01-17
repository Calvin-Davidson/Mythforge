package nl.trifox.mythforge.Characters;

import java.util.UUID;

public class CharacterData {
    private final String Name;
    private final UUID uuid;

    public CharacterData(UUID uuid, String name) {
        this.Name = name;
        this.uuid = uuid;
    }

    public UUID GetUuid() {
        return uuid;
    }

    public String GetName() {
        return this.Name;
    }
}
