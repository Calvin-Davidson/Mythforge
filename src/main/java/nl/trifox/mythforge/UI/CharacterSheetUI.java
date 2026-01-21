package nl.trifox.mythforge.UI;

import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.player.pages.BasicCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import nl.trifox.mythforge.Characters.CharacterData;
import nl.trifox.mythforge.Utils.e5Helper;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class CharacterSheetUI extends BasicCustomUIPage {

    private final CharacterData characterData;

    public CharacterSheetUI(@NonNullDecl PlayerRef playerRef, @NonNullDecl CustomPageLifetime lifetime, CharacterData characterData) {
        super(playerRef, lifetime);
        this.characterData = characterData;
    }

    @Override
    public void build(UICommandBuilder ui) {
        ui.append("CharacterSheet.ui");

        ui.set("#MFName.TextSpans", Message.raw(characterData.getName()));
        ui.set("#MFMeta.TextSpans", Message.raw(
                "Level " + characterData.getLevel()
                        + " - " + characterData.getCharacterClass()
                        + " - " + characterData.getRace()
        ));
        ui.set("#MFStatus.TextSpans", Message.raw("Status: " + (characterData.getIsActive() ? "ACTIVE" : "INACTIVE")));

        int pb = e5Helper.proficiencyBonus(characterData.getLevel());
        ui.set("#MFStatLine1.TextSpans", Message.raw(
                "HP: " + characterData.getCurrentHealth() + "/" + characterData.getMaxHealth()
                        + "  |  AC: " + characterData.getArmorClass()
        ));
        ui.set("#MFStatLine2.TextSpans", Message.raw(
                "PB: +" + pb
                        + "  |  Speed: " + characterData.getSpeed() + " ft"
        ));

        ui.set("#MFAbRow1.TextSpans", Message.raw(
                "STR: " + characterData.getStrength() + " (" + modPrefix(e5Helper.abilityMod(characterData.getStrength())) + ")"
                        + "  |  DEX: " + characterData.getDexterity() + " (" + modPrefix(e5Helper.abilityMod(characterData.getDexterity())) + ")"
        ));
        ui.set("#MFAbRow2.TextSpans", Message.raw(
                "CON: " + characterData.getConstitution() + " (" + modPrefix(e5Helper.abilityMod(characterData.getConstitution())) + ")"
                        + "  |  INT: " + characterData.getIntelligence() + " (" + modPrefix(e5Helper.abilityMod(characterData.getIntelligence())) + ")"
        ));
        ui.set("#MFAbRow3.TextSpans", Message.raw(
                "WIS: " + characterData.getWisdom() + " (" + modPrefix(e5Helper.abilityMod(characterData.getWisdom())) + ")"
                        + "  |  CHA: " + characterData.getCharisma() + " (" + modPrefix(e5Helper.abilityMod(characterData.getCharisma())) + ")"
        ));
    }



    private static String modPrefix(int mod) {
        return (mod >= 0 ? "+" : "") + mod;
    }

}
