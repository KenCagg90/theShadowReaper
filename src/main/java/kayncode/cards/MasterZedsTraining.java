package kayncode.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.powers.MasterZedsTrainingPower;
import kayncode.relics.special.Rhaast;
import kayncode.relics.special.ShadowAssassin;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;
import static kayncode.KaynMod.makeImagePath;

public class MasterZedsTraining extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(MasterZedsTraining.class.getSimpleName());

    public MasterZedsTraining() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
        setOrbTexture(makeImagePath("512/energyMix.png"), makeImagePath("1024/energyMix.png"));
        MultiCardPreview.add(this, new DemonArmor(), new AssassinsTraining());
        MultiCardPreview.horizontalOnly(this);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new MasterZedsTrainingPower(p, this.magicNumber), this.magicNumber));
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