package kayncode.relics;

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

import static kayncode.KaynMod.makeID;

public class InfinityEdge extends AbstractEasyRelic {
    public static final String ID = makeID("InfinityEdge");

    private int strengthGiven;

    public InfinityEdge() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
        this.strengthGiven = 0;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (!target.hasPower(VulnerablePower.POWER_ID)) {
            return;
        }

        this.flash();
        int hits = calculateHits(strengthGiven);

        for (int i = 0; i < hits; i++) {
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1)));
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, 1)));
            strengthGiven++;
        }

        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    private int calculateHits(int strengthGiven) {
        if (strengthGiven >= 10) {
            return 3;
        } else if (strengthGiven >= 5) {
            return 2;
        } else  {
            return 1;
         }
    }

    @Override
    public void onVictory() {
        this.strengthGiven = 0; // Reset strengthGiven at the end of combat
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
