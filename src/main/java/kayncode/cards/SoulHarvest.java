package kayncode.cards;

import kayncode.actions.SoulHarvestAction;
import kayncode.powers.ReapPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static kayncode.KaynMod.makeID;

public class SoulHarvest extends AbstractEasyCard {
    public final static String ID = makeID(SoulHarvest.class.getSimpleName());

    public SoulHarvest() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = this.magicNumber = 10;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDeadOrEscaped()) {
                this.addToBot(new ApplyPowerAction(monster, p, new ReapPower(monster, this.magicNumber), this.magicNumber));
            }
        }

        // Add custom action to calculate the total Reap and apply block
        this.addToBot(new SoulHarvestAction(p));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(4);
    }
}
