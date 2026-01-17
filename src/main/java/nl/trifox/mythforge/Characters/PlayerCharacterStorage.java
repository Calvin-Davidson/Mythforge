package nl.trifox.mythforge.Characters;

import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public void save(CharacterData data) {
        File file = new File(dataFolder, data.GetUuid().toString() + data.GetName() + ".json");

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
}
