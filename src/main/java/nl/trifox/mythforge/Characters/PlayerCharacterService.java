package nl.trifox.mythforge.Characters;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerCharacterService {
    private final PlayerCharacterStorage storage;
    private final Map<UUID, CharacterData> activeCharacterCache;

    public PlayerCharacterService(PlayerCharacterStorage storage) {
        this.storage = storage;
        this.activeCharacterCache = new ConcurrentHashMap<>();
        loadAllActive();
    }

    private void loadAllActive() {
        for (CharacterData data : storage.loadAllActive()) {
            activeCharacterCache.put(data.getUuid(), data);
        }
    }

    public CharacterData getActivePlayerCharacter(UUID uuid) {
        return activeCharacterCache.computeIfAbsent(uuid, id -> storage.loadActive(uuid));
    }

    public CharacterData getCharacter(UUID uuid, String Name) {
        return storage.load(uuid, Name);
    }

    public List<CharacterData> getCharacters(UUID uuid) {
        return storage.loadAll().stream().filter(characterData -> characterData.getUuid().equals(uuid)).toList();
    }

    public void saveCharacter(CharacterData data) {
        activeCharacterCache.put(data.getUuid(), data);
        storage.save(data);
    }

    public void saveAll() {
        for (CharacterData data : activeCharacterCache.values()) {
            storage.save(data);
        }
    }

    public CharacterData setActive(UUID uuid, String name) {
        var activeCharacter = getActivePlayerCharacter(uuid);
        var character = getCharacter(uuid, name);

        activeCharacter.setIsActive(false);
        character.setIsActive(true);
        storage.save(activeCharacter);
        storage.save(character);

        activeCharacterCache.put(uuid, character);
        return character;
    }

    public boolean delete(CharacterData character) {
        if (character.getIsActive()) {
            activeCharacterCache.remove(character.getUuid());
        }
        return storage.delete(character);
    }
}