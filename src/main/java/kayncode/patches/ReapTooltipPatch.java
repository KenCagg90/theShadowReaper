package kayncode.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import kayncode.powers.ReapPower;
import kayncode.util.Wiz;

public class ReapTooltipPatch {

    @SpirePatch2(clz = EnergyPanel.class, method = "addEnergy")
    public static class add {
        @SpirePostfixPatch()
        public static void PostFix() {
            updateReap();
        }
    }

    @SpirePatch2(clz = EnergyPanel.class, method = "useEnergy")
    public static class use {
        @SpirePostfixPatch()
        public static void PostFix() {
            updateReap();
        }
    }

    @SpirePatch2(clz = EnergyPanel.class, method = "setEnergy")
    public static class set {
        @SpirePostfixPatch()
        public static void PostFix() {
            updateReap();
        }
    }

    private static void updateReap() {
        for (AbstractMonster m : Wiz.getEnemies()) {
            AbstractPower reap = m.getPower(ReapPower.POWER_ID);
            if (reap != null) {
                reap.updateDescription();
            }
        }
    }
}