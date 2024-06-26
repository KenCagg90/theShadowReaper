package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static kayncode.KaynMod.makeID;

public class ShedWeakness extends AbstractEasyCard {
    public final static String ID = makeID(ShedWeakness.class.getSimpleName());

    public ShedWeakness() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 4;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        p.damage(new DamageInfo(p, this.magicNumber, DamageInfo.DamageType.HP_LOSS)); // Player loses HP
        int debuffCount = p.powers.stream()
                .filter(power -> power.type == AbstractPower.PowerType.DEBUFF)
                .mapToInt(power -> power.amount)
                .sum();
        this.addToBot(new RemoveDebuffsAction(p)); // Remove all debuffs
        if (debuffCount > 0) {
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, debuffCount), debuffCount)); // Gain strength for each debuff stack removed
        }
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(-1); // Upgrade to lose 3 HP instead of 4
    }
}