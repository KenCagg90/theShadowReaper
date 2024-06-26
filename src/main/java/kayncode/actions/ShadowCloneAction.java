package kayncode.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.Iterator;

public class ShadowCloneAction extends AbstractGameAction {
    private AbstractPlayer player;
    private boolean upgraded;

    public ShadowCloneAction(boolean upgraded) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.player.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            AbstractDungeon.handCardSelectScreen.open("Choose a card to clone.", 1, false, false, false, false, true);
            this.tickDuration();
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                Iterator<AbstractCard> iterator = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

                while (iterator.hasNext()) {
                    AbstractCard selectedCard = iterator.next();
                    AbstractCard clonedCard = selectedCard.makeStatEquivalentCopy();
                    clonedCard.setCostForTurn(0);
                    player.hand.addToHand(selectedCard);

                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(clonedCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                }

                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            }

            this.tickDuration();
        }
    }
}
