package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import code.actions.HalfReapDamageAction;
import code.powers.ReapPower;

import static code.ModFile.makeID;

public class EdgeOfDeath extends AbstractEasyCard {
    public final static String ID = makeID(EdgeOfDeath.class.getSimpleName());

    public EdgeOfDeath() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = 6;
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
        upgradeMagicNumber(2); // Upgrade to apply 8 Reap instead of 6
    }
    }
