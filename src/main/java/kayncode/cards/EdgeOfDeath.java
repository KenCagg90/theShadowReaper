package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.actions.HalfReapDamageAction;
import kayncode.powers.ReapPower;

import static kayncode.KaynMod.makeID;

public class EdgeOfDeath extends AbstractEasyCard {
    public final static String ID = makeID(EdgeOfDeath.class.getSimpleName());

    public EdgeOfDeath() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Apply Reap
        this.addToTop(new ApplyPowerAction(m, p, new ReapPower(m, this.magicNumber), this.magicNumber));

        // Deal damage based on Reap amount
        this.addToBot(new HalfReapDamageAction(p, m));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2); // Upgrade to apply 8 Reap instead of 5
    }
    }

