package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static kayncode.KaynMod.makeID;

public class SanguineStrike extends AbstractEasyCard {
    public final static String ID = makeID(SanguineStrike.class.getSimpleName());

    public SanguineStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 15;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        p.damage(new DamageInfo(p, 2, DamageInfo.DamageType.HP_LOSS)); // Player loses 2 HP
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
    }

    @Override
    public void upp() {
        this.upgradeDamage(-5); // Upgrade to deal 10 damage instead of 15
        this.upgradeMagicNumber(1); // Upgrade to apply 2 Vulnerable instead of 1
    }
}
