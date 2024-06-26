package kayncode.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;

public class StackTheDeckAction extends AbstractGameAction {
    private boolean pickCard = false;
    private int amount;

    public StackTheDeckAction(int amount) {
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (!pickCard) {
            if (AbstractDungeon.player.exhaustPile.isEmpty()) {
                isDone = true;
                return;
            }
            pickCard = true;
            int availableCards = AbstractDungeon.player.exhaustPile.size();
            AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.exhaustPile, Math.min(amount, availableCards), "Select cards to place on top of your draw pile", false);
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(card.makeStatEquivalentCopy(), true, false));
                    AbstractDungeon.player.exhaustPile.removeCard(card); // Remove the card from the exhaust pile
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                isDone = true;
            }
            tickDuration();
        }
    }
}
