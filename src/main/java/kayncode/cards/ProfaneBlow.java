package kayncode.cards;

import kayncode.actions.ProfaneBlowAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class ProfaneBlow extends AbstractEasyCard {
    public final static String ID = makeID(ProfaneBlow.class.getSimpleName());

    public ProfaneBlow() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 30;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ProfaneBlowAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 2));
    }

    @Override
    public void upp() {
        upgradeDamage(5);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ProfaneBlow();
    }
}
