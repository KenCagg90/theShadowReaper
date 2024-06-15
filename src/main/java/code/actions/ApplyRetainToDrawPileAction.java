package code.actions;

import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ApplyRetainToDrawPileAction extends AbstractGameAction {
    private boolean pickCard = false;

    @Override
    public void update() {
        if (!pickCard) {
            if (AbstractDungeon.player.drawPile.isEmpty()) {
                isDone = true;
                return;
            }
            pickCard = true;
            AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.drawPile, 1, "Select a card to Retain", false);
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                    CardModifierManager.addModifier(card, new RetainMod());
                    card.flash();
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                isDone = true;
            }
        }
    }
}