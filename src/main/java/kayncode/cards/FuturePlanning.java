package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class FuturePlanning extends AbstractEasyCard {
    public final static String ID = makeID(FuturePlanning.class.getSimpleName());

    public FuturePlanning() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int cardsInHand = p.hand.size();
        this.addToBot(new DrawCardAction(p, cardsInHand));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0); // Upgrade to cost 0 energy
    }
}
