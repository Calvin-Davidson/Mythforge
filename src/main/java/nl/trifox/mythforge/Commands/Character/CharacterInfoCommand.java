package nl.trifox.mythforge.Commands.Character;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.OptionalArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.entity.entities.Player;
import nl.trifox.mythforge.Characters.PlayerCharacterService;
import nl.trifox.mythforge.MythForge;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class CharacterInfoCommand extends CommandBase {
    private final MythForge mythForge;
    private final PlayerCharacterService PlayerCharacterService;
    private final OptionalArg<String> CharacterName;

    public CharacterInfoCommand(@NonNullDecl String name, @NonNullDecl String description, MythForge mythForge, PlayerCharacterService playerCharacterService) {
        super(name, description);
        this.mythForge = mythForge;
        PlayerCharacterService = playerCharacterService;
        this.CharacterName = this.withOptionalArg("Character name", "if you want details about a specific character, defaults to the active character", ArgTypes.STRING);
    }



    protected void executeSync(@NonNullDecl CommandContext commandContext) {
        var sender = commandContext.sender();

        if (sender instanceof Player) {
            var character = CharacterName.provided(commandContext) ?
                    PlayerCharacterService.getCharacter(sender.getUuid(), CharacterName.get(commandContext)) :
                    PlayerCharacterService.getActivePlayerCharacter(sender.getUuid());

            if (character == null) {
                sender.sendMessage(Message.raw("You have no active character, or this character does not exist"));
            } else {
                var characterClass = character.getCharacterClass() != null ? character.getCharacterClass() : "Unknown";
                var characterRace = character.getRace() != null ? character.getRace() : "Unknown";

                sender.sendMessage(Message.raw(mythForge.TextConfig.get().CharacterInfoSheet
                        .replace("%class%", characterClass )
                        .replace("%race%", characterRace)
                        .replace("%level%", String.valueOf(character.getLevel())).
                        replace("%name%", character.getName())));
            }
        }
    }
}
