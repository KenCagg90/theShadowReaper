package kayncode.relics.uncommon;

import basemod.patches.com.megacrit.cardcrawl.relics.AbstractRelic.RelicOutlineColor;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import kayncode.TheShadowReaper;
import kayncode.relics.AbstractEasyRelic;

import static kayncode.KaynMod.makeID;

public class FirstStrike extends AbstractEasyRelic {
    public static final String ID = makeID("FirstStrike");
    private int attacksCounter = 0;

    public FirstStrike() {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void atBattleStart() {
        this.counter = 3;
        this.attacksCounter = 0;
        this.grayscale = false;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.owner == AbstractDungeon.player && damageAmount > 0 && attacksCounter <= 2 && info.type == DamageInfo.DamageType.NORMAL) {
            int goldToGain = (int) Math.ceil(damageAmount * 0.2f);
            AbstractDungeon.player.gainGold(goldToGain);
            this.counter--;
            attacksCounter++;
            this.flash();

            for (int i = 0; i < goldToGain; i++) {
                AbstractDungeon.effectList.add(new GainPennyEffect(AbstractDungeon.player, target.hb.cX, target.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, true));
            }
        }
    }

    @Override
    public void atTurnStart() {
        if (this.attacksCounter >= 3) {
            this.counter = -1;
            this.grayscale = true;
        }
    }

    @Override
    public void onVictory() {
        this.counter = -1;
        this.attacksCounter = 0;
        this.grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}