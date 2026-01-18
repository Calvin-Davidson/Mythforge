package nl.trifox.mythforge.Commands.Character;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.entity.entities.Player;
import nl.trifox.mythforge.Characters.CharacterMessageFormatter;
import nl.trifox.mythforge.Characters.PlayerCharacterService;
import nl.trifox.mythforge.Consts.MessageID;
import nl.trifox.mythforge.MythForge;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class ChangeCharacterCommand extends CommandBase {

    private final PlayerCharacterService PlayerCharacterService;
    private final RequiredArg<String> CharacterName;

    public ChangeCharacterCommand(@NonNullDecl String name, @NonNullDecl String description, MythForge mythForge, PlayerCharacterService playerCharacterService) {
        super(name, description);
        PlayerCharacterService = playerCharacterService;
        this.CharacterName = this.withRequiredArg("Character name", "to which character do you wish the swap", ArgTypes.STRING);
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {
        var sender = commandContext.sender();

        if (sender instanceof Player) {
            var character = PlayerCharacterService.getCharacter(sender.getUuid(), CharacterName.get(commandContext));

            if (character == null) {
                sender.sendMessage(Message.translation(MessageID.CharacterDoesNotExists));
            } else {
                var activatedCharacter = PlayerCharacterService.setActive(sender.getUuid(), CharacterName.get(commandContext));
                var message = CharacterMessageFormatter.Format(Message.translation(MessageID.ChangedToCharacter), activatedCharacter);
                sender.sendMessage(Message.raw(message.getAnsiMessage()));
            }
        }
    }
}
