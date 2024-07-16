package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class BloodGambit extends AbstractEasyCard {
    public final static String ID = makeID(BloodGambit.class.getSimpleName());

    public BloodGambit() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 3;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Lose HP
        this.addToBot(new LoseHPAction(p, p, this.magicNumber));
        // Gain Energy
        this.addToBot(new GainEnergyAction(1));
        // Draw 1 card
        this.addToBot(new DrawCardAction(1));
    }

    @Override
    public void upp() {
        this.exhaust = false; // Upgrade to lose 2 HP instead of 3
    }

}