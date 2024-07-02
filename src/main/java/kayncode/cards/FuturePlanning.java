package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import kayncode.actions.ConserveEnergyAction;

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
        this.addToBot(new ConserveEnergyAction(p, 1));
        this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, cardsInHand), cardsInHand));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0); // Upgrade to cost 0 energy
    }
}
