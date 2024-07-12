package kayncode.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class StrategicManeuverAction extends AbstractGameAction {
    private boolean pickCard = false;
    private int numCardsToSelect;

    public StrategicManeuverAction(int numCardsToSelect) {
        this.numCardsToSelect = numCardsToSelect;
    }

    @Override
    public void update() {
        if (!pickCard) {
            if (AbstractDungeon.player.drawPile.isEmpty()) {
                isDone = true;
                return;
            }
            pickCard = true;
            int actualNumCardsToSelect = Math.min(AbstractDungeon.player.drawPile.size(), this.numCardsToSelect);
            AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.drawPile, actualNumCardsToSelect,
                    "Select up to " + this.numCardsToSelect + " cards to upgrade and place at the top of the draw pile", false);
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                // Upgrade and move the selected cards to the top of the draw pile in the order they were picked
                for (int i = AbstractDungeon.gridSelectScreen.selectedCards.size() - 1; i >= 0; i--) {
                    AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(i);
                    if (card.canUpgrade()) {
                        card.upgrade();
                        card.superFlash();
                        card.applyPowers();
                    }
                    AbstractDungeon.player.drawPile.moveToDeck(card, false);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                isDone = true;
            }
        }
    }
}