package code.powers;

import code.actions.UmbralTrespassAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import code.util.Wiz;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static code.ModFile.makeID;

public class UTPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("UTPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private final boolean isRhaast;
    private final boolean isShadow;

    public UTPower(AbstractCreature owner, int amount, boolean isRhaast, boolean isShadow) {
        super(POWER_ID, powerStrings.NAME, PowerType.DEBUFF, true, owner, amount);
        this.isRhaast = isRhaast;
        this.isShadow = isShadow;
    }

    public void atEndOfRound() {
        int finalDamage = amount;

        if (owner.currentHealth < owner.maxHealth * 0.3) {
            finalDamage *= 2;
        }

        if (owner.hasPower(VulnerablePower.POWER_ID)) {
            finalDamage *= AbstractDungeon.player.hasRelic("Paper Phrog") ? 1.75 : 1.5;
        }

        addToBot(new UmbralTrespassAction(owner, finalDamage, isRhaast, isShadow));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }


    @Override
    public void updateDescription() {
        description = "Next turn, deal " + amount + " damage to the target. If the target is below 30% HP, deal " + amount * 2 + ".";
    }
}
