package code.powers;

import code.actions.UmbralTrespassAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import code.util.Wiz;

import static code.ModFile.makeID;

public class UTPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("UTPower");
    private final boolean isRhaast;
    private final boolean isShadow;

    public UTPower(AbstractCreature owner, int amount, boolean isRhaast, boolean isShadow) {
        super(POWER_ID, "UTPower", PowerType.DEBUFF, true, owner, amount);
        this.isRhaast = isRhaast;
        this.isShadow = isShadow;
    }

    @Override
    public void atStartOfTurn() {
        int finalDamage = (owner.currentHealth < owner.maxHealth * 0.3) ? amount * 2 : amount;
        addToBot(new UmbralTrespassAction(owner, finalDamage, isRhaast, isShadow));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        description = "Next turn, deal " + amount + " damage to the target. If the target is below 30% HP, deal " + amount * 2 + ".";
    }
}
