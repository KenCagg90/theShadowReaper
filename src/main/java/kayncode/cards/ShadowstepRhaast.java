package kayncode.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import kayncode.relics.BaseForm;
import kayncode.relics.ShadowAssassin;
import kayncode.relics.TheDarkinScythe;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;

import static kayncode.KaynMod.makeImagePath;

public class ShadowstepRhaast extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(ShadowstepRhaast.class.getSimpleName());

    public ShadowstepRhaast() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 3;
        this.baseBlock = 6;
        setBackgroundTexture(makeImagePath("512/attackRhaast.png"), makeImagePath("1024/attackRhaast.png"));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.magicNumber), this.magicNumber));
        blck();


    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeBlock(2); // Optional: upgrade the block amount if you want
    }

    @Override
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
        if (AbstractDungeon.player.hasRelic(ShadowAssassin.ID) || AbstractDungeon.player.hasRelic(BaseForm.ID) || AbstractDungeon.player.hasRelic(TheDarkinScythe.ID) ) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean canSpawnShop(ArrayList<AbstractCard> currentRewardCards) {
        if (AbstractDungeon.player.hasRelic(ShadowAssassin.ID) || AbstractDungeon.player.hasRelic(BaseForm.ID) || AbstractDungeon.player.hasRelic(TheDarkinScythe.ID) ) {
            return false;
        }
        else {
            return true;
        }
    }

    }



