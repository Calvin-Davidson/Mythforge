package nl.trifox.mythforge.Characters;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class PlayerCharacterStorage implements IPlayerStorage {

    private final File dataFolder;

    public PlayerCharacterStorage(File dataFolder) {
        this.dataFolder = dataFolder;
        var _ = this.dataFolder.mkdirs();
    }

    @Override
    public CharacterData load(UUID uuid, String characterName) {
        File file = new File(dataFolder, uuid.toString() + characterName + ".json");
        if (!file.exists()) return null;

        try (FileReader reader = new FileReader(file)) {
            return new Gson().fromJson(reader, CharacterData.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CharacterData loadActive(UUID uuid) {
        return loadAllActive().stream().filter(characterData -> characterData.getUuid().equals(uuid)).findFirst().orElseGet(() -> null);
    }

    @Override
    public void save(CharacterData data) {
        File file = new File(dataFolder, data.getUuid().toString() + data.getName() + ".json");

        try (FileWriter writer = new FileWriter(file)) {
            new Gson().toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CharacterData> loadAll() {
        List<CharacterData> players = new ArrayList<>();
        File[] files = dataFolder.listFiles((dir, name) -> name.endsWith(".json"));

        if (files != null) {
            for (File file : files) {
                try (FileReader reader = new FileReader(file)) {
                    players.add(new Gson().fromJson(reader, CharacterData.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return players;
    }

    @Override
    public List<CharacterData> loadAllActive() {
        var all = loadAll();
        return all.stream().filter(CharacterData::getIsActive).toList();
    }

    @Override
    public boolean delete(CharacterData data) {
        return Arrays.stream(Objects.requireNonNull(dataFolder.listFiles((dir, name) -> name.equals(data.getUuid().toString() + data.getName() + ".json")))).findFirst().orElseThrow().delete();
    }
}
