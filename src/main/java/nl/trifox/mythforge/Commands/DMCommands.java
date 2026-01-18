package nl.trifox.mythforge.Commands;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import nl.trifox.mythforge.Commands.Dice.RollCommand;
import nl.trifox.mythforge.MythForge;
import nl.trifox.mythforge.Consts.Permissions;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class DMCommands extends CommandBase  {

    public DMCommands(MythForge MythForge) {
        super("DM", "All commands the DM might execute");

        this.addSubCommand(new RollCommand(MythForge, true, Permissions.DMDiceRoll));
        this.requirePermission(Permissions.DM);
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {

    }
}

