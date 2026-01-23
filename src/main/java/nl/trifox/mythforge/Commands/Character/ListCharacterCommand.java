package nl.trifox.mythforge.Commands.Character;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.entity.entities.Player;
import nl.trifox.mythforge.Characters.CharacterData;
import nl.trifox.mythforge.Characters.PlayerCharacterService;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class ListCharacterCommand extends CommandBase {
    private final PlayerCharacterService PlayerCharacterService;

    public ListCharacterCommand(@NonNullDecl String name, @NonNullDecl String description, PlayerCharacterService playerCharacterService) {
        super(name, description);
        PlayerCharacterService = playerCharacterService;
    }


    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {
        var sender = commandContext.sender();

        if (sender instanceof Player player) {
            var characters = PlayerCharacterService.getCharacters(sender.getUuid());;
            StringBuilder result = new StringBuilder("player characters");
            for (CharacterData characterData : characters) {
                result.append("\n")
                        .append(characterData.getName())
                        .append(" is a lvl: ")
                        .append(characterData.getLevel())
                        .append(" ")
                        .append(characterData.getCharacterClass());
            }
            sender.sendMessage(Message.raw(result.toString()));
        }
    }
}

