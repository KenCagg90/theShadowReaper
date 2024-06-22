package code.relics;

import code.CharacterFile;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static code.ModFile.makeID;

public class FirstStrike extends AbstractEasyRelic {
    public static final String ID = makeID("FirstStrike");
    private float damageCounter = 0;

    public FirstStrike() {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK, CharacterFile.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void atBattleStart() {
        this.counter = 0; // Reset the counter at the start of each combat
        this.damageCounter = 0;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.owner == AbstractDungeon.player && damageAmount > 0 && AbstractDungeon.actionManager.turn <= 3) {
            damageCounter += damageAmount / 10;
            this.counter = (int) Math.ceil(damageCounter); // Update the counter to reflect accumulated damage
            this.flash();
        }
    }

    @Override
    public void onVictory() {
        if (damageCounter > 0) {
            int goldToGain = this.counter = (int) Math.ceil(damageCounter);
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