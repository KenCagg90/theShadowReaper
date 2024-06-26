package kayncode.patches;

import kayncode.cards.*;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(clz = AbstractDungeon.class, method = "nextRoomTransition", paramtypez = {})
public class KaynTransformCardsPatch {

    public static void transformToRhaast() {
        replaceCardsInDeckWithUpgradeCheck(BladesReach.ID, new BladesReachRhaast());
        replaceCardsInDeckWithUpgradeCheck(ReapingSlash.ID, new ReapingSlashRhaast());
        replaceCardsInDeckWithUpgradeCheck(Shadowstep.ID, new ShadowstepRhaast());
        replaceCardsInDeckWithUpgradeCheck(UmbralTrespass.ID, new UmbralTrespassRhaast());
        replaceCardsInDeckWithUpgradeCheck(FlurryOfBlades.ID, new FlurryOfBladesRhaast());
        replaceCardsInDeckWithUpgradeCheck(FrenziedStrike.ID, new FrenziedStrikeRhaast());
    }

    public static void transformToAssassin() {
        replaceCardsInDeckWithUpgradeCheck(BladesReach.ID, new BladesReachAssassin());
        replaceCardsInDeckWithUpgradeCheck(ReapingSlash.ID, new ReapingSlashAssassin());
        replaceCardsInDeckWithUpgradeCheck(Shadowstep.ID, new ShadowstepAssassin());
        replaceCardsInDeckWithUpgradeCheck(UmbralTrespass.ID, new UmbralTrespassAssassin());
        replaceCardsInDeckWithUpgradeCheck(FlurryOfBlades.ID, new FlurryOfBladesAssassin());
        replaceCardsInDeckWithUpgradeCheck(FrenziedStrike.ID, new FrenziedStrikeAssassin());
    }

    private static void replaceCardsInDeckWithUpgradeCheck(String targetCardId, AbstractCard replacementCard) {
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (card.cardID.equals(targetCardId)) {
                AbstractCard newCard = replacementCard.makeCopy();
                if (card.upgraded) {
                    newCard.upgrade();
                }
                int index = AbstractDungeon.player.masterDeck.group.indexOf(card);
                AbstractDungeon.player.masterDeck.group.set(index, newCard);
            }
        }
    }
}
