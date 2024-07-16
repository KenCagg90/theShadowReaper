package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import kayncode.util.Wiz;

import static kayncode.KaynMod.makeID;

public class SiphonStrength extends AbstractEasyCard {
    public final static String ID = makeID(SiphonStrength.class.getSimpleName());

    public SiphonStrength() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.magicNumber = this.baseMagicNumber = 1;
        this.secondMagic = this.baseSecondMagic = 2;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int enemyCount = 0;
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.isDead && !monster.isDying) {
                enemyCount++;
            }
        }
        // Player gains strength
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber * enemyCount), this.magicNumber * enemyCount));

        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.isDead && !monster.isDying) {
                // Monster loses strength equal to magicNumber
                this.addToBot(new ApplyPowerAction(monster, p, new StrengthPower(monster, -this.magicNumber), -this.magicNumber));

                // Reduce enemy's strength by 3(4) for this turn
                this.addToBot(new ApplyPowerAction(monster, p, new StrengthPower(monster, -this.secondMagic), -this.secondMagic));

                // Apply GainStrengthPower only if the monster doesn't have Artifact
                if (!monster.hasPower("Artifact")) {
                    this.addToBot(new ApplyPowerAction(monster, p, new GainStrengthPower(monster, this.secondMagic), this.secondMagic));
                }
            }
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
