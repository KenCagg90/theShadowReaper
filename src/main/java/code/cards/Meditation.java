package code.cards;

import code.actions.ConserveEnergyAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class Meditation extends AbstractEasyCard {
    public final static String ID = makeID(Meditation.class.getSimpleName());

    public Meditation() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(1));
        this.addToBot(new ConserveEnergyAction(p, 1));
    }

    @Override
    public void upp() {
        this.exhaust = false;
    }
}
