package kayncode.relics;

import kayncode.TheShadowReaper;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static kayncode.KaynMod.makeID;

public class FirstStrike extends AbstractEasyRelic {
    public static final String ID = makeID("FirstStrike");
    private float damageCounter = 0;
    private int attacksCounter = 0;

    public FirstStrike() {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void atBattleStart() {
        this.counter = 0; // Reset the counter at the start of each combat
        this.damageCounter = 0;
        this.attacksCounter = 0; // Reset the attacks counter
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.owner == AbstractDungeon.player && damageAmount > 0 && attacksCounter < 4) {
            counter += damageAmount * 0.2; // Accumulate 20% of the damage
            attacksCounter++; // Increment the attacks counter
            this.flash();

        }
    }

    @Override
    public void onVictory() {
        if (counter > 0) {
            int goldToGain = (int) Math.ceil(counter);
            RewardItem goldReward = new RewardItem(goldToGain);
            goldReward.text = goldReward.text + " (First Strike)";
            AbstractDungeon.getCurrRoom().rewards.add(goldReward);
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new FirstStrike();
    }
}
