package kayncode.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.relics.special.Rhaast;
import kayncode.relics.special.ShadowAssassin;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;
import static kayncode.KaynMod.makeImagePath;

public class ShadowSight extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(ShadowSight.class.getSimpleName());
    private static int totalPlaysThisTurn = 0;

    public ShadowSight() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        selfRetain = true;
        this.baseMagicNumber = this.magicNumber = 1;
        this.exhaust = true;
        setOrbTexture(makeImagePath("512/energyMix.png"), makeImagePath("1024/energyMix.png"));
        MultiCardPreview.add(this, new ShadowSightRhaast(), new ShadowSightAssassin());
        MultiCardPreview.horizontalOnly(this);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(this.magicNumber));
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
}
