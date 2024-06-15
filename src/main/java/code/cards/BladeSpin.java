package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class BladeSpin extends AbstractEasyCard {
    public final static String ID = makeID(BladeSpin.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public BladeSpin() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 7;
        isMultiDamage = true;
        selfRetain = true;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}