package nl.trifox.mythforge;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import nl.trifox.mythforge.Characters.PlayerCharacterService;
import nl.trifox.mythforge.Characters.PlayerCharacterStorage;
import nl.trifox.mythforge.Commands.CharacterCommand;
import nl.trifox.mythforge.Commands.DMCommands;
import nl.trifox.mythforge.Commands.Dice.RollCommand;
import nl.trifox.mythforge.Consts.Permissions;

import javax.annotation.Nonnull;

/**
 * This class serves as the entrypoint for your plugin. Use the setup method to register into game registries or add
 * event listeners.
 */
public class MythForge extends JavaPlugin {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    public final PlayerCharacterService CharacterService;

    public MythForge(@Nonnull JavaPluginInit init) {
        super(init);

        this.CharacterService = new PlayerCharacterService(new PlayerCharacterStorage(init.getDataDirectory().resolve("characters").toFile()));
    }

    @Override
    protected void setup() {
        LOGGER.atInfo().log( "Setting up plugin " + this.getName());

        this.getCommandRegistry().registerCommand(new RollCommand(this, false, Permissions.DiceRoll));
        this.getCommandRegistry().registerCommand(new DMCommands(this));
        this.getCommandRegistry().registerCommand(new CharacterCommand(this.CharacterService));

        LOGGER.atInfo().log( "Setup completed for " + this.getName());

    }
}