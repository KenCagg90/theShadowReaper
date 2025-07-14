package kayncode.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import kayncode.relics.BaseForm;
import kayncode.relics.Rhaast;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import kayncode.relics.TheDarkinScythe;
import kayncode.util.Wiz;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;
import static kayncode.KaynMod.makeImagePath;

public class BladesReachAssassin extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(BladesReachAssassin.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public BladesReachAssassin() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 7;
        this.baseMagicNumber = this.magicNumber = 1; // Used for Weak/Vulnerable
        setBackgroundTexture(makeImagePath("512/attackAssassin.png"), makeImagePath("1024/attackAssassin.png"));
        setOrbTexture(makeImagePath("512/energyAssassin.png"), makeImagePath("1024/energyAssassin.png"));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {



                allDmg(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
                 Wiz.forAllMonstersLiving(monster -> Wiz.applyToEnemy(monster, new VulnerablePower(p, this.magicNumber, false)));

    }

    @Override
    public void upp() {
        upgradeDamage(3); // Upgrade to deal 15 damage instead of 10
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

