package nl.trifox.mythforge.Characters;

import java.util.List;
import java.util.UUID;

public interface IPlayerStorage {
    CharacterData load(UUID uuid, String CharacterName);
    void save(CharacterData data);
    List<CharacterData> loadAll();
}
