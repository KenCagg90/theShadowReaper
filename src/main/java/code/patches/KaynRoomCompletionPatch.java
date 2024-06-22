package code.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import code.relics.TheDarkinScythe;

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

        System.out.println("RoomCompletionPatch: Orbs to give = " + orbsToGive);

        TheDarkinScythe scythe = (TheDarkinScythe) AbstractDungeon.player.getRelic(TheDarkinScythe.ID);
        if (scythe != null) {
            if (isRedRoom) {
                System.out.println("RoomCompletionPatch: Red room detected. Adding RedOrbs.");
                TheDarkinScythe.redOrbs += orbsToGive;
                scythe.flashRed();
            } else if (isBlueRoom && TheDarkinScythe.blueOrbs < 8) {
                System.out.println("RoomCompletionPatch: Blue room detected. Adding BlueOrbs.");
                TheDarkinScythe.blueOrbs += orbsToGive;
                scythe.flashBlue();
            }
            scythe.updateDescription();
            scythe.checkOrbCounts();
        }
    }
}
