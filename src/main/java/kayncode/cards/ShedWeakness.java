package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.HashSet;
import java.util.Set;

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

        // Use a set to count unique debuffs including LoseStrengthPower
        Set<String> uniqueDebuffs = new HashSet<>();
        p.powers.stream()
                .filter(power -> power.type == AbstractPower.PowerType.DEBUFF)
                .forEach(power -> uniqueDebuffs.add(power.ID));

        // Remove all debuffs
        p.powers.stream()
                .filter(power -> power.type == AbstractPower.PowerType.DEBUFF)
                .forEach(power -> addToBot(new RemoveSpecificPowerAction(p, p, power)));

        if (!uniqueDebuffs.isEmpty()) {
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, uniqueDebuffs.size()), uniqueDebuffs.size())); // Gain strength for each unique debuff removed
        }
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(-1); // Upgrade to lose 3 HP instead of 4
    }
}
