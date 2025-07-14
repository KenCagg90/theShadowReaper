package kayncode.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import kayncode.actions.UmbralTrespassAction;
import kayncode.actions.UmbralTrespassRhaastAction;

import static kayncode.KaynMod.makeID;

public class UTPowerRhaast extends AbstractEasyPower {
    public static final String POWER_ID = makeID("UTPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);


    public UTPowerRhaast(AbstractCreature owner, int amount) {
        super(POWER_ID, powerStrings.NAME, PowerType.BUFF, true, owner, amount);
    }

    public void onInitialApplication()
    {
        type = PowerType.DEBUFF;
    }

    public void atEndOfRound() {
        int finalDamage = amount;

        if (owner.hasPower(VulnerablePower.POWER_ID)) {
            finalDamage *= AbstractDungeon.player.hasRelic("Paper Phrog") ? 1.75 : 1.5;
        }

        addToBot(new UmbralTrespassRhaastAction(owner, finalDamage));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }


    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
    }
}
