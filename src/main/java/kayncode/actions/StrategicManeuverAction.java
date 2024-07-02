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
                    "Select up to " + this.numCardsToSelect + " cards to place at the top of the draw pile", false);
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                    AbstractDungeon.player.drawPile.moveToDeck(card, false);
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                isDone = true;
            }
        }
    }
}