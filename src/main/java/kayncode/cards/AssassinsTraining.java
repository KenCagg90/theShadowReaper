package kayncode.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import kayncode.powers.AssassinsTrainingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.relics.special.BaseForm;
import kayncode.relics.special.Rhaast;
import kayncode.relics.special.TheDarkinScythe;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;
import static kayncode.KaynMod.makeImagePath;

public class AssassinsTraining extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(AssassinsTraining.class.getSimpleName());

    public AssassinsTraining() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        setBackgroundTexture(makeImagePath("512/attackAssassin.png"), makeImagePath("1024/attackAssassin.png"));
        setOrbTexture(makeImagePath("512/energyAssassin.png"), makeImagePath("1024/energyAssassin.png"));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new AssassinsTrainingPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1); // Upgrades the card to cost 0 energy
    }
    @Override
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
        if (AbstractDungeon.player.hasRelic(Rhaast.ID) || AbstractDungeon.player.hasRelic(BaseForm.ID) || AbstractDungeon.player.hasRelic(TheDarkinScythe.ID) ) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean canSpawnShop(ArrayList<AbstractCard> currentRewardCards) {
        if (AbstractDungeon.player.hasRelic(Rhaast.ID) || AbstractDungeon.player.hasRelic(BaseForm.ID) || AbstractDungeon.player.hasRelic(TheDarkinScythe.ID) ) {
            return false;
        }
        else {
            return true;
        }
    }
}
