package kayncode.actions;

import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import kayncode.cards.ShadowClone;

import java.util.ArrayList;

public class ShadowCloneAction extends AbstractGameAction {
    private boolean pickCard = false;
    private boolean upgraded;

    public ShadowCloneAction(boolean upgraded) {
        this.upgraded = upgraded;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (!pickCard) {
            if (AbstractDungeon.player.hand.isEmpty()) {
                isDone = true;
                return;
            }

            // Filter out ShadowSight cards
            ArrayList<AbstractCard> tempGroup = new ArrayList<>();
            for (AbstractCard card : AbstractDungeon.player.hand.group) {
                if (!card.cardID.equals(ShadowClone.ID)) {
                    tempGroup.add(card);
                }
            }

            if (tempGroup.isEmpty()) {
                isDone = true;
                return;
            }

            // Convert ArrayList to CardGroup
            CardGroup tempCardGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            tempCardGroup.group.addAll(tempGroup);

            pickCard = true;
            AbstractDungeon.gridSelectScreen.open(tempCardGroup, 1, "Choose a card to clone.", false, false, false, false);
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard selectedCard : AbstractDungeon.gridSelectScreen.selectedCards) {
                    AbstractCard clonedCard = selectedCard.makeStatEquivalentCopy();
                    clonedCard.setCostForTurn(Math.max(0, selectedCard.cost - 1)); // Ensure cost doesn't go below 0
                    CardModifierManager.addModifier(clonedCard, new EtherealMod());
                    AbstractDungeon.player.hand.addToHand(clonedCard);
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(clonedCard, Settings.WIDTH, Settings.HEIGHT));
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                isDone = true;
            }
            tickDuration();
        }
    }
}
