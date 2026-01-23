package nl.trifox.mythforge.Characters;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPlayerStorage {
    CharacterData load(UUID uuid, String CharacterName);
    CharacterData loadActive(UUID uuid);

    void save(CharacterData data);
    List<CharacterData> loadAll();
    List<CharacterData> loadAllActive();

    boolean delete(CharacterData data);
}
