package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.powers.HeavyHitterPower;

import static kayncode.KaynMod.makeID;

public class HeavyHitter extends AbstractEasyCard {
    public final static String ID = makeID(HeavyHitter.class.getSimpleName());

    public HeavyHitter() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new HeavyHitterPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(1); // Upgrades to give 2 energy
    }

}
