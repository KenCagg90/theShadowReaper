package code.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import code.relics.Rhaast;
import code.relics.ShadowAssassin;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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

public class BladesReach extends AbstractEasyCard {
    public final static String ID = makeID(BladesReach.class.getSimpleName());
    private static final String RELIC_A_ID = Rhaast.ID; // Replace with the actual ID of relic A
    private static final String RELIC_B_ID = ShadowAssassin.ID; // Replace with the actual ID of relic B
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public BladesReach() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 1; // Used for Weak/Vulnerable

        MultiCardPreview.add(this, new BladesReachRhaast(), new BladesReachAssassin());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MonsterGroup monsters = AbstractDungeon.getMonsters();
        int count = 0;

        for (AbstractMonster monster : monsters.monsters) {
            if (monster.isDeadOrEscaped()) continue;
            this.addToBot(new DamageAction(monster, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            count++;
            if (count >= 2) break; // Only attack the first two alive monsters
        }

    }

    @Override
    public void upp() {
        upgradeDamage(4); // Upgrade to deal 12 damage instead of 8
    }



    @Override
    public AbstractCard makeCopy() {
        if (AbstractDungeon.player != null) {
            if (AbstractDungeon.player.hasRelic(Rhaast.ID)) {
                return new BladesReachRhaast();
            } else if (AbstractDungeon.player.hasRelic(ShadowAssassin.ID)) {
                return new BladesReachAssassin();
            }
        }
        return new BladesReach();
    }
}
