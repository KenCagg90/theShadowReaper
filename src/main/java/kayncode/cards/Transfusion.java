package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class Transfusion extends AbstractEasyCard {
    public final static String ID = makeID(Transfusion.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , ,

    public Transfusion() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        baseBlock = 10;
        baseDamage = 18;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        blck();
    }

    @Override
    public void upp() {

        upgradeBlock(3);
        upgradeDamage(6);
    }
}