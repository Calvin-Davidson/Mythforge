package nl.trifox.mythforge.Commands.Dice;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.entity.entities.Player;
import nl.trifox.mythforge.Dice.DiceParser;
import nl.trifox.mythforge.Dice.DiceResultFormatter;
import nl.trifox.mythforge.MythForge;

import javax.annotation.Nonnull;
import java.util.Objects;

public class RollCommand extends CommandBase {

    public MythForge MythForge;

    public RequiredArg<String> Roll;
    public boolean IsForDM;

    public RollCommand(MythForge mythForge, boolean isForDM, String requiredPermission) {
        super("roll", "Rolls the dice");
        this.Roll = this.withRequiredArg("role properties", "provide your roll e.g. 1d20", ArgTypes.STRING);
        this.IsForDM = isForDM;
        this.MythForge = mythForge;
        this.requirePermission(requiredPermission);
    }

    @Override
    protected void executeSync(@Nonnull CommandContext ctx) {
        var parseResult = DiceParser.roll(ctx.get(Roll));
        CommandSender sender = ctx.sender();

        if (sender instanceof Player player) {
            if (this.IsForDM) {
                var text = DiceResultFormatter.Format(MythForge.TextConfig.get().DMDiceRollText, parseResult);
                sender.sendMessage(Message.raw(text));
            } else {
                var players = player.getReference().getStore().getExternalData().getWorld().getPlayerRefs();

                var textSelf = DiceResultFormatter.Format(MythForge.TextConfig.get().DiceRollSelfText, parseResult);
                var textOthers  = DiceResultFormatter.Format(MythForge.TextConfig.get().DiceRollOthersText, parseResult);

                players.forEach(playerRef -> {
                    if (Objects.requireNonNull(playerRef.getReference()).equals(player.getReference())) {
                        playerRef.sendMessage(Message.raw(textSelf));
                    } else {
                        playerRef.sendMessage(Message.raw(textOthers));
                    }
                });

            }
        }



    }
}