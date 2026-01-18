package nl.trifox.mythforge.Characters;

import java.util.UUID;

public class CharacterData {
    private final String Name;
    private final UUID uuid;
    private boolean IsActive;

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

    public void SetIsActive(boolean isActive) {
        this.IsActive = isActive;
    }

    public Boolean GetIsActive() {
        return this.IsActive;
    }
}
