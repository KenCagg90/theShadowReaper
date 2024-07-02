package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import kayncode.actions.EasyXCostAction;

import static kayncode.KaynMod.makeID;

public class ShowersOfBlood extends AbstractEasyCard {
    public final static String ID = makeID(ShowersOfBlood.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public ShowersOfBlood() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 7;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                this.addToTop(new LoseHPAction(p, p, this.magicNumber, AbstractGameAction.AttackEffect.NONE));
                this.addToTop(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            }
            return true;
        }));

        this.addToBot(new LoseEnergyAction(EnergyPanel.totalCount));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}