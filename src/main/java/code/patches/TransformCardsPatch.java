package code.patches;

import basemod.ReflectionHacks;
import code.cards.BladesReach;
import code.cards.BladesReachRhaast;
import code.cards.BladesReachAssassin;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

@SpirePatch(clz = AbstractDungeon.class, method = "nextRoomTransition", paramtypez = {})
public class TransformCardsPatch {

    public static void transformToRhaast() {
        replaceCardsInDeck(BladesReach.ID, new BladesReachRhaast());
    }

    public static void transformToAssassin() {
        replaceCardsInDeck(BladesReach.ID, new BladesReachAssassin());
    }

    private static void replaceCardsInDeck(String targetCardId, AbstractCard replacementCard) {
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (card.cardID.equals(targetCardId)) {
                int index = AbstractDungeon.player.masterDeck.group.indexOf(card);
                AbstractDungeon.player.masterDeck.group.set(index, replacementCard.makeCopy());
            }
        }
    }
}
