package kayncode.cards;

import kayncode.powers.ReapPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class ReaperSigil extends AbstractEasyCard {
    public final static String ID = makeID(ReaperSigil.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public ReaperSigil() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new ReapPower(m, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new ReapPower(m, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(4);
    }
}
