package kayncode.relics;

import kayncode.TheShadowReaper;
import kayncode.patches.KaynTransformCardsPatch;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class Rhaast extends AbstractEasyRelic {
    public static final String ID = makeID("Rhaast");

    public Rhaast() {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void onEquip() {
        CardCrawlGame.sound.play("RHAAST_TRANSFORM", 0.0F);
        KaynTransformCardsPatch.transformToRhaast();
    }

    @Override
    public void onObtainCard(AbstractCard c) {
        KaynTransformCardsPatch.transformToRhaast();
    }

    @Override
    public void onUnequip() {
        if (AbstractDungeon.player instanceof TheShadowReaper) {
            ((TheShadowReaper) AbstractDungeon.player).resetToDefaultAppearance();
        }
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.owner == AbstractDungeon.player && damageAmount > 0) {
            if (info.type == DamageInfo.DamageType.NORMAL) {
                this.flash();
                int tempHP = (int) Math.ceil(damageAmount * 0.10);
                AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, tempHP));
            } else if (info.type == DamageInfo.DamageType.HP_LOSS && target instanceof AbstractMonster) {
                this.flash();
                int tempHP = (int) Math.ceil(damageAmount * 0.08);
                AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, tempHP));
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
