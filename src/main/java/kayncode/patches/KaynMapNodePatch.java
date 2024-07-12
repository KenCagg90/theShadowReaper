package kayncode.patches;

import kayncode.relics.special.TheDarkinScythe;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kayncode.KaynMod.makeImagePath;
import basemod.ReflectionHacks;

@SpirePatch(clz = MapRoomNode.class, method = "render", paramtypez = SpriteBatch.class)
public class KaynMapNodePatch {

    private static final Texture RED_GLOW_ELITE = ImageMaster.loadImage(makeImagePath("map/red_glow_elite.png"));
    private static final Texture BLUE_GLOW_ELITE = ImageMaster.loadImage(makeImagePath("map/blue_glow_elite.png"));
    private static final Texture RED_GLOW_MONSTER = ImageMaster.loadImage(makeImagePath("map/red_glow_monster.png"));
    private static final Texture BLUE_GLOW_MONSTER = ImageMaster.loadImage(makeImagePath("map/blue_glow_monster.png"));
    private static final Map<MapRoomNode, Boolean> nodeColors = new HashMap<>();
    private static final Map<MapRoomNode, Boolean> isColored = new HashMap<>();

    private static boolean roomsProcessed = false;
    private static final float PERCENTAGE = 1.0f; // Change this to set the percentage of colored rooms

    @SpirePrefixPatch
    public static void mapRenderingPatch(MapRoomNode __instance, SpriteBatch sb) {
        if (!roomsProcessed) {
            processRooms();
            roomsProcessed = true;
        }

        float offsetX = (float)ReflectionHacks.getPrivateStatic(MapRoomNode.class, "OFFSET_X");
        float offsetY = (float)ReflectionHacks.getPrivateStatic(MapRoomNode.class, "OFFSET_Y");
        float spacingX = (float)ReflectionHacks.getPrivateStatic(MapRoomNode.class, "SPACING_X");
        float scale = (float)ReflectionHacks.getPrivate(__instance, MapRoomNode.class, "scale");

        boolean hasScythe = AbstractDungeon.player.hasRelic(TheDarkinScythe.ID);

        if (hasScythe) {
            if (__instance.room instanceof MonsterRoom || __instance.room instanceof MonsterRoomElite) {
                if (!isColored.getOrDefault(__instance, false)) return;

                boolean isRed = nodeColors.getOrDefault(__instance, false);
                Texture glowTexture = null;

                if (__instance.room instanceof MonsterRoomElite) {
                    glowTexture = isRed ? RED_GLOW_ELITE : BLUE_GLOW_ELITE;
                } else if (__instance.room instanceof MonsterRoom) {
                    glowTexture = isRed ? RED_GLOW_MONSTER : BLUE_GLOW_MONSTER;
                }

                if (glowTexture != null) {
                    Color originalColor = sb.getColor();
                    sb.setColor(1, 1, 1, 0.50f); // Set opacity to 75%

                    sb.setBlendFunction(770, 1); // Set the blend function for additive blending

                    // Draw the glow
                    sb.draw(glowTexture,
                            __instance.x * spacingX + offsetX - 64.0f + __instance.offsetX,
                            __instance.y * Settings.MAP_DST_Y + offsetY + DungeonMapScreen.offsetY - 64.0f + __instance.offsetY,
                            64.0f,
                            64.0f,
                            128.0f,
                            128.0f,
                            (scale * 0.65f + 0.2f) * Settings.scale,
                            (scale * 0.65f + 0.2f) * Settings.scale,
                            0,
                            0,
                            0,
                            128,
                            128,
                            false,
                            false);

                    sb.setBlendFunction(770, 771); // Reset the blend function to normal
                    sb.setColor(originalColor); // Reset color to original
                }
            }
        }
    }

    private static void processRooms() {
        List<MapRoomNode> eligibleNodes = new ArrayList<>();
        for (ArrayList<MapRoomNode> row : AbstractDungeon.map) {
            for (MapRoomNode node : row) {
                if (node.room instanceof MonsterRoom || node.room instanceof MonsterRoomElite) {
                    eligibleNodes.add(node);
                }
            }
        }

        int totalNodes = eligibleNodes.size();
        int halfNodes = totalNodes / 2;

        Collections.shuffle(eligibleNodes, AbstractDungeon.mapRng.random);

        // Ensure at least half are red and half are blue
        for (int i = 0; i < totalNodes; i++) {
            MapRoomNode node = eligibleNodes.get(i);
            isColored.put(node, true);
            nodeColors.put(node, i < halfNodes);
        }

        System.out.println("Processed rooms for coloring:");
        for (MapRoomNode node : eligibleNodes) {
            System.out.println("Node at (" + node.x + ", " + node.y + ") - " + (nodeColors.get(node) ? "Red" : "Blue"));
        }
    }

    public static void resetRoomsProcessed() {
        roomsProcessed = false;
        isColored.clear();
        nodeColors.clear();
    }

    public static boolean isRedRoom(MapRoomNode node) {
        return nodeColors.getOrDefault(node, false);
    }

    public static boolean isBlueRoom(MapRoomNode node) {
        return !nodeColors.getOrDefault(node, false);
    }

    public static boolean isColoredRoom(MapRoomNode node) {
        return isColored.getOrDefault(node, false);
    }
}
