package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class Bloodfury extends AbstractEasyCard {
    public final static String ID = makeID(Bloodfury.class.getSimpleName());

    public Bloodfury() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new LoseHPAction(p, p, this.magicNumber));
        this.addToBot(new GainEnergyAction(3));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(-1); // Upgrades to lose 5 HP instead of 8
    }
}
