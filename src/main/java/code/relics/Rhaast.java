package code.relics;

import code.CharacterFile;
import code.cards.BladesReach;
import code.cards.BladesReachRhaast;
import code.patches.TransformCardsPatch;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import static code.ModFile.makeID;

public class Rhaast extends AbstractEasyRelic {
    public static final String ID = makeID("Rhaast");

    public Rhaast() {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK, CharacterFile.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void onEquip() {
        CardCrawlGame.sound.play("RHAAST_TRANSFORM", 0.0F);
        TransformCardsPatch.transformToRhaast();
        CardLibrary.cards.remove(BladesReach.ID); CardLibrary.cards.put(BladesReachRhaast.ID, new BladesReachRhaast());
    }

    @Override
    public void onObtainCard(AbstractCard c) {
       TransformCardsPatch.transformToRhaast();
    }


    @Override
    public void onUnequip() {
        if (AbstractDungeon.player instanceof CharacterFile) {
            ((CharacterFile) AbstractDungeon.player).resetToDefaultAppearance();
        }
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.owner == AbstractDungeon.player && info.type == DamageInfo.DamageType.NORMAL && damageAmount > 0) {
            this.flash();
            int tempHP = (int) Math.ceil(damageAmount * 0.15);
            AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, tempHP));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }
}
