package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import static code.ModFile.makeID;

public class TemporalStrike extends AbstractEasyCard {
    public final static String ID = makeID(TemporalStrike.class.getSimpleName());

    public TemporalStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
        this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, 2), 2));
    }

    @Override
    public void upp() {
        upgradeDamage(5); // Upgrade to deal 20 damage instead of 15
    }
}
