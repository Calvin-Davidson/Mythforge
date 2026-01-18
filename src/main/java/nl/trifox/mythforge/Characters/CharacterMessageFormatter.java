package nl.trifox.mythforge.Characters;

import com.hypixel.hytale.server.core.Message;
import nl.trifox.mythforge.Consts.Macro;

public class CharacterMessageFormatter {
    public static Message Format(Message message, CharacterData character) {
        var characterClass = character.getCharacterClass() != null ? character.getCharacterClass() : "Unknown";
        var characterRace = character.getRace() != null ? character.getRace() : "Unknown";

        return message
                .param(Macro.CharacterClass, character.getCharacterClass())
                .param(Macro.CharacterClass, characterClass )
                .param(Macro.CharacterRace, characterRace)
                .param(Macro.CharacterLevel, String.valueOf(character.getLevel()))
                .param(Macro.CharacterName, character.getName());
    }
}
