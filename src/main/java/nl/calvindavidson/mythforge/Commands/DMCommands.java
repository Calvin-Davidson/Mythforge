package nl.calvindavidson.mythforge.Commands;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import nl.calvindavidson.mythforge.Commands.Dice.RollCommand;
import nl.calvindavidson.mythforge.Utils.Permissions;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class DMCommands extends CommandBase  {

    public RequiredArg<String> Roll;

    public DMCommands() {
        super("DM", "All commands the DM might execute");

        this.addSubCommand(new RollCommand(true, Permissions.DMDiceRoll));


    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {

    }
}

