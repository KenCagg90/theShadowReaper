package kayncode.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import kayncode.relics.Rhaast;
import kayncode.relics.ShadowAssassin;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;
import static kayncode.KaynMod.makeImagePath;

public class Shadowstep extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(Shadowstep.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , ,

    public Shadowstep() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        setOrbTexture(makeImagePath("512/energyMix.png"), makeImagePath("1024/energyMix.png"));
        MultiCardPreview.add(this, new ShadowstepRhaast(), new ShadowstepAssassin());
        MultiCardPreview.horizontalOnly(this);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.magicNumber), this.magicNumber));

    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        for (AbstractCard c : MultiCardPreview.multiCardPreview.get(this)) {
            c.upgrade();
        }
    }

    @Override
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
        if (AbstractDungeon.player.hasRelic(Rhaast.ID) || AbstractDungeon.player.hasRelic(ShadowAssassin.ID) ) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean canSpawnShop(ArrayList<AbstractCard> currentRewardCards) {
        if (AbstractDungeon.player.hasRelic(Rhaast.ID) || AbstractDungeon.player.hasRelic(ShadowAssassin.ID) ) {
            return false;
        }
        else {
            return true;
        }
    }


//    @Override
//    public AbstractCard makeCopy() {
//        if (AbstractDungeon.player != null) {
//            if (AbstractDungeon.player.hasRelic(Rhaast.POWER_ID)) {
//                return new ShadowstepRhaast();
//            } else if (AbstractDungeon.player.hasRelic(ShadowAssassin.POWER_ID)) {
//                return new ShadowstepAssassin();
//            }
//        }
//        return new Shadowstep();
//    }


}



