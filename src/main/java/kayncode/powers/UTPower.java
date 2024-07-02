package kayncode.powers;

import kayncode.actions.UmbralTrespassAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static kayncode.KaynMod.makeID;

public class UTPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("UTPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private final boolean isRhaast;
    private final boolean isShadow;

    public UTPower(AbstractCreature owner, int amount, boolean isRhaast, boolean isShadow) {
        super(POWER_ID, powerStrings.NAME, PowerType.BUFF, true, owner, amount);
        this.isRhaast = isRhaast;
        this.isShadow = isShadow;
    }

    public void atEndOfRound() {
        int finalDamage = amount;

        if (owner.hasPower(VulnerablePower.POWER_ID)) {
            finalDamage *= AbstractDungeon.player.hasRelic("Paper Phrog") ? 1.75 : 1.5;
        }

        addToBot(new UmbralTrespassAction(owner, finalDamage, isRhaast, isShadow));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }


    @Override
    public void updateDescription() {
        description = "Next turn, deal " + amount + " damage to the target.";
    }
}
