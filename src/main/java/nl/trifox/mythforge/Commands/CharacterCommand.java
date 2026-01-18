package nl.trifox.mythforge.Commands;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import nl.trifox.mythforge.Characters.PlayerCharacterService;
import nl.trifox.mythforge.Commands.Character.*;
import nl.trifox.mythforge.MythForge;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class CharacterCommand extends CommandBase {

    public CharacterCommand(MythForge mythForge, PlayerCharacterService playerCharacterService) {
        super("character", "manage your characters");

        addSubCommand(new ChangeCharacterCommand("Change", "Changes the current character", mythForge, playerCharacterService));
        addSubCommand(new CharacterInfoCommand("info", "get's information about your current character", mythForge, playerCharacterService));
        addSubCommand(new CreateCharacterCommand("Create", "Creates a new character", mythForge, playerCharacterService));
        addSubCommand(new DeleteCharacterCommand("Delete", "Deletes a character", mythForge, playerCharacterService));
        addSubCommand(new ListCharacterCommand("List", "List all character", mythForge,  playerCharacterService));
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {
    }
}
