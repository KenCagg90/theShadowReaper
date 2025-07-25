package kayncode.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import kayncode.relics.TheDarkinScythe;

@SpirePatch(clz = AbstractRoom.class, method = "endBattle")
public class KaynRoomCompletionPatch {

    @SpirePostfixPatch
    public static void onRoomCompletion(AbstractRoom __instance) {
        if (!KaynMapNodePatch.isColoredRoom(AbstractDungeon.getCurrMapNode())) {
            System.out.println("RoomCompletionPatch: Room is not colored. No orbs to add.");
            return;
        }

        boolean isRedRoom = KaynMapNodePatch.isRedRoom(AbstractDungeon.getCurrMapNode());
        boolean isBlueRoom = KaynMapNodePatch.isBlueRoom(AbstractDungeon.getCurrMapNode());
        int orbsToGive = (__instance instanceof MonsterRoomElite) ? 2 : 1;

        if (__instance instanceof MonsterRoomBoss) {
            orbsToGive = 1; // Boss room gives 1 of each type
        }

        System.out.println("RoomCompletionPatch: Orbs to give = " + orbsToGive);

        TheDarkinScythe scythe = (TheDarkinScythe) AbstractDungeon.player.getRelic(TheDarkinScythe.ID);
        if (scythe != null) {
            if (__instance instanceof MonsterRoomBoss) {
                // Boss room, give 1 of each type
                if (TheDarkinScythe.redOrbs < 8) {
                    TheDarkinScythe.redOrbs += orbsToGive;
                    scythe.flashRed();
                }
                if (TheDarkinScythe.blueOrbs < 8) {
                    TheDarkinScythe.blueOrbs += orbsToGive;
                    scythe.flashBlue();
                }
            } else {
                // Normal rooms
                if (isRedRoom && TheDarkinScythe.redOrbs < 8) {
                    System.out.println("RoomCompletionPatch: Red room detected. Adding RedOrbs.");
                    TheDarkinScythe.redOrbs += orbsToGive;
                    scythe.flashRed();
                } else if (isBlueRoom && TheDarkinScythe.blueOrbs < 8) {
                    System.out.println("RoomCompletionPatch: Blue room detected. Adding BlueOrbs.");
                    TheDarkinScythe.blueOrbs += orbsToGive;
                    scythe.flashBlue();
                }
            }
            scythe.updateDescription();
            scythe.checkOrbCounts();
        }
    }
}
