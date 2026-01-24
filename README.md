# 🎲 MythForge

**MythForge** is a lightweight Hytale plugin that brings **tabletop RPG dice rolling** into your server. Perfect for 5e style campaigns, it allows players and DMs to roll dice in-game with standard RPG notation. Future updates will include campaign tools.

---

## ✨ Features

- 🎯 **Dice Rolling Parser**  
  Roll dice using standard notation like `1D20`, `3D6`, or modifiers. Results show both **individual rolls** and **totals**.

- ⚖️ **Advantage / Disadvantage Support**  
  Roll with advantage (`adv`) or disadvantage (`dis`) directly in-game.

- 🛡 **DM Tools**  
  DM only rolls

---

## ⚡ Commands

| Command | Description |
|---------|-------------|
| `/roll 1D20` | Roll a standard 20-sided die. |
| `/roll 1D20adv` | Roll a 20-sided die with **advantage** (take the higher of two rolls). |
| `/roll 1D20dis` | Roll a 20-sided die with **disadvantage** (take the lower of two rolls). |

---

## Permissions


| Permission                          | Description                                                     |
|-------------------------------------|-----------------------------------------------------------------|
| `MythForge.Core.DiceRoll`            | Allows a player to roll dice.                                   |
| `MythForge.DM`                      | Base permission required for all DM commands.                   |
| `MythForge.DM.DiceRoll`             | Allows rolling dice as a Dungeon Master.                        |
| `MythForge.Character`               | Base permission for all character-related commands.             |
| `MythForge.Character.Create`        | Allows creating a new character.                                |
| `MythForge.Character.Info`          | Allows viewing basic character information.                     |
| `MythForge.Character.Sheet`         | Allows opening the full character sheet UI.                     |
| `MythForge.Character.Change`        | Allows modifying character data (name, class, race, etc.).     |
| `MythForge.Character.Delete`        | Allows deleting a character.                                    |
| `MythForge.Character.List`          | Allows listing all owned characters.                            |
| `MythForge.Character.SetStat`       | Allows setting or adjusting character stats.                    |


## 🎨 About

Created by **Trifox Studios**, MythForge is designed to bring **classic RPG dice mechanics** into Hytale in a simple, fun, and flexible way—perfect for DMs and players who love hands-on campaigns.

---

