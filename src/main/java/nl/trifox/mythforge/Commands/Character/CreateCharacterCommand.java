package nl.trifox.mythforge.Commands.Character;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import nl.trifox.mythforge.Characters.PlayerCharacterService;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class CreateCharacterCommand extends CommandBase {
    private final PlayerCharacterService PlayerCharacterService;

    public CreateCharacterCommand(@NonNullDecl String name, @NonNullDecl String description, PlayerCharacterService playerCharacterService) {
        super(name, description);
        PlayerCharacterService = playerCharacterService;
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {

    }
}

