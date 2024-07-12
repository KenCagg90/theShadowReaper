package kayncode.relics.boss;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import kayncode.TheShadowReaper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import kayncode.relics.AbstractEasyRelic;

import static kayncode.KaynMod.makeID;

public class InfinityEdge extends AbstractEasyRelic {
    public static final String ID = makeID("InfinityEdge");

    public InfinityEdge() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.type != DamageInfo.DamageType.NORMAL || damageAmount <= 0 || !target.hasPower(VulnerablePower.POWER_ID)) {
            return;
        }

        this.flash();
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1)));
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, 1)));

        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public void onVictory() {
        // No longer need to reset any counters at the end of combat
    }

    @Override
    public AbstractRelic makeCopy() {
        return new InfinityEdge();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
