package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import kayncode.powers.ReapPower;

import static kayncode.KaynMod.makeID;

public class SoulHarvest extends AbstractEasyCard {
    public final static String ID = makeID(SoulHarvest.class.getSimpleName());

    public SoulHarvest() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = this.magicNumber = 10;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int totalBlock = 0;

        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDeadOrEscaped()) {
                this.addToBot(new ApplyPowerAction(monster, p, new ReapPower(monster, this.magicNumber), this.magicNumber));
                if (!monster.hasPower(ArtifactPower.POWER_ID)) {
                    totalBlock += 5;
                }
            }
        }

        if (totalBlock > 0) {
            this.addToBot(new GainBlockAction(p, totalBlock));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(4);
    }
}
