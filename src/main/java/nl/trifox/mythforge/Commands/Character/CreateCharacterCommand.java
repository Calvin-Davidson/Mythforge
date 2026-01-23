package nl.trifox.mythforge.Commands.Character;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.entity.entities.Player;
import nl.trifox.mythforge.Characters.CharacterData;
import nl.trifox.mythforge.Characters.CharacterMessageFormatter;
import nl.trifox.mythforge.Characters.PlayerCharacterService;
import nl.trifox.mythforge.Consts.MessageID;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class CreateCharacterCommand extends CommandBase {
    private final PlayerCharacterService PlayerCharacterService;

    private final RequiredArg<String> CharacterName;

    public CreateCharacterCommand(@NonNullDecl String name, @NonNullDecl String description, PlayerCharacterService playerCharacterService) {
        super(name, description);
        PlayerCharacterService = playerCharacterService;
        this.CharacterName = withRequiredArg("CharacterName", "the name of the character", ArgTypes.STRING);
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {
        var sender = commandContext.sender();

        if (sender instanceof Player) {
            var character = PlayerCharacterService.getCharacter(sender.getUuid(), CharacterName.get(commandContext));

            if (character != null) {
                var message = CharacterMessageFormatter.Format(Message.translation(MessageID.CharacterAlreadyExists), character);
                sender.sendMessage(Message.raw(message.getAnsiMessage()));
                return;
            }

            var characterData = new CharacterData(sender.getUuid(), CharacterName.get(commandContext));
            PlayerCharacterService.saveCharacter(characterData);

            var message = CharacterMessageFormatter.Format(Message.translation(MessageID.CharacterCreated), characterData);
            sender.sendMessage(Message.raw(message.getAnsiMessage()));
        }
    }
}

