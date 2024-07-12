package kayncode.relics.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.green.PiercingWail;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import kayncode.TheShadowReaper;
import kayncode.relics.AbstractEasyRelic;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.cards.DamageInfo;

import static kayncode.KaynMod.makeID;

public class Aftershock extends AbstractEasyRelic {
    public static final String ID = makeID("Aftershock");
    private static final int BLOCK_AMOUNT = 10;
    private static final int DAMAGE_AMOUNT = 10;
    private static final int TURN_ACTIVATION = 3;
    private boolean hasTriggered = false;

    public Aftershock() {
        super(ID, RelicTier.RARE, LandingSound.HEAVY, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }


    @Override
    public void atBattleStart() {
        counter = 0;
        this.hasTriggered = false;// Reset turn counter at the start of each combat
    }

    @Override
    public void atTurnStart() {
        if (!this.grayscale) {
            ++this.counter;
        }
        if (counter == 4 && !hasTriggered) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, BLOCK_AMOUNT));
            CardCrawlGame.sound.play("AFTERSHOCK", 0.0F);
            this.addToBot(new VFXAction(AbstractDungeon.player, new ShockWaveEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.ADDITIVE), 0.3F));
            this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(DAMAGE_AMOUNT, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
            this.grayscale = true;
            this.hasTriggered = true;
        }
    }


    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }



    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BLOCK_AMOUNT + DESCRIPTIONS[1] + DAMAGE_AMOUNT + DESCRIPTIONS[2];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Aftershock();
    }
}
