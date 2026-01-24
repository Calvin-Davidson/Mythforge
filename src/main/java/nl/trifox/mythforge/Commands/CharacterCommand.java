package nl.trifox.mythforge.Commands;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import nl.trifox.mythforge.Characters.PlayerCharacterService;
import nl.trifox.mythforge.Commands.Character.*;
import nl.trifox.mythforge.Consts.Permissions;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class CharacterCommand extends CommandBase {

    public CharacterCommand(PlayerCharacterService playerCharacterService) {
        super("character", "manage your characters");
        this.addAliases("char");
        this.requirePermission(Permissions.CharacterBase);

        addSubCommand(new ChangeCharacterCommand("Change", "Changes the current character", playerCharacterService));
        addSubCommand(new CharacterInfoCommand("info", "get's information about your current character", playerCharacterService));
        addSubCommand(new CreateCharacterCommand("Create", "Creates a new character", playerCharacterService));
        addSubCommand(new DeleteCharacterCommand("Delete", "Deletes a character", playerCharacterService));
        addSubCommand(new ListCharacterCommand("List", "List all character", playerCharacterService));
        addSubCommand(new SetCharacterCommand("set", "Set's properties of a character", playerCharacterService));
        addSubCommand(new CharacterSheetCommand("sheet", "Opens the player character sheet", playerCharacterService));
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {
    }
}
