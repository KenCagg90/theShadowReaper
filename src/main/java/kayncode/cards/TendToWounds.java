package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static kayncode.KaynMod.makeID;

public class TendToWounds extends AbstractEasyCard {
    public final static String ID = makeID(TendToWounds.class.getSimpleName());
    
    public TendToWounds() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 6;
        this.exhaust = true;
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new HealAction(p, p, this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false), 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(3);
    }
}
