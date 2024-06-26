package kayncode.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import kayncode.TheShadowReaper;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

import static kayncode.KaynMod.makeID;

public class TheCollector extends AbstractEasyRelic {
    public static final String ID = makeID("TheCollector");

    public TheCollector() {
        super(ID, RelicTier.SHOP, LandingSound.CLINK, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        AbstractDungeon.player.gainGold(5);
        this.flash();
        for (int i = 0; i < 5; i++) {
            AbstractDungeon.effectList.add(new GainPennyEffect(AbstractDungeon.player, m.hb.cX, m.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, true));
        }
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount == 999) return;

        if (!target.isPlayer) {
            float healthThreshold = target.maxHealth * 0.05f;
            if ((target.currentHealth - damageAmount) <= healthThreshold || target.currentHealth <= healthThreshold) {
                this.addToBot(new SFXAction("COLLECTOR"));
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                target.currentHealth = 0;
                target.healthBarUpdatedEvent();
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new TheCollector();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
