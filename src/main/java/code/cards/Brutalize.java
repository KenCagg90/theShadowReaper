package code.cards;

import code.powers.ReapPower;
import code.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static code.ModFile.makeID;
import static com.megacrit.cardcrawl.powers.VulnerablePower.POWER_ID;

public class Brutalize extends AbstractEasyCard {
    public final static String ID = makeID(Brutalize.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Brutalize() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 14;
        this.baseMagicNumber = this.magicNumber = 2;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if (!m.hasPower("Vulnerable")) {
            this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false)));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
    }
}