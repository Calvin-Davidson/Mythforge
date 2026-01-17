package nl.trifox.mythforge.Characters;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerCharacterService {
    private final PlayerCharacterStorage storage;
    private final Map<UUID, CharacterData> cache;

    public PlayerCharacterService(PlayerCharacterStorage storage) {
        this.storage = storage;
        this.cache = new ConcurrentHashMap<>();
        loadAll();
    }

    private void loadAll() {
        for (CharacterData data : storage.loadAll()) {
            cache.put(data.GetUuid(), data);
        }
    }

    public CharacterData getPlayer(UUID uuid, String characterName) {
        return cache.computeIfAbsent(uuid, id -> {
            CharacterData data = storage.load(id, characterName);
            return data != null ? data : new CharacterData(id, characterName);
        });
    }

    public void savePlayer(CharacterData data) {
        cache.put(data.GetUuid(), data);
        storage.save(data);
    }

    public void saveAll() {
        for (CharacterData data : cache.values()) {
            storage.save(data);
        }
    }
}