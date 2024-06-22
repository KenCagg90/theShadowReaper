package code.cards;

import code.relics.Rhaast;
import code.relics.ShadowAssassin;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static code.ModFile.makeID;
import static code.ModFile.makeImagePath;

public class BladesReachAssassin extends AbstractEasyCard {
    public final static String ID = makeID(BladesReachAssassin.class.getSimpleName());
    private static final String RELIC_A_ID = Rhaast.ID; // Replace with the actual ID of relic A
    private static final String RELIC_B_ID = ShadowAssassin.ID; // Replace with the actual ID of relic B
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public BladesReachAssassin() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 1; // Used for Weak/Vulnerable
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MonsterGroup monsters = AbstractDungeon.getMonsters();
        int count = 0;

        for (AbstractMonster monster : monsters.monsters) {
            if (monster.isDeadOrEscaped()) continue;
                this.addToBot(new DamageAction(monster, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                this.addToBot(new ApplyPowerAction(monster, p, new VulnerablePower(monster, this.magicNumber, false), this.magicNumber));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(4); // Upgrade to deal 15 damage instead of 10
    }


    @Override
    public void applyPowers() {
        super.applyPowers();
        updateTitleAndDescription(AbstractDungeon.player);
    }

    private void updateTitleAndDescription(AbstractPlayer p) {

            this.rawDescription = cardStrings.DESCRIPTION;
        }

    }

