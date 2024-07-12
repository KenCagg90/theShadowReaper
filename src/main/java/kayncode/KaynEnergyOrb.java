package kayncode;

import basemod.abstracts.CustomEnergyOrb;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import kayncode.relics.special.TheDarkinScythe;

public class KaynEnergyOrb extends CustomEnergyOrb {
    private static final Color RED_TINT = new Color(1.0F, 0.60F, 0.60F, 1F);
    private static final Color BLUE_TINT = new Color(0.60F, 0.60F, 1.0F, 1F);
    private Color currentTint = new Color(1F, 1F, 1F, 1F);  // Start with no tint

    public KaynEnergyOrb(String[] orbTexturePaths, String orbVfxPath, float[] layerSpeeds) {
        super(orbTexturePaths, orbVfxPath, layerSpeeds);
    }

    @Override
    public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
        updateTint();

        Color originalColor = sb.getColor().cpy();
        Color tintedColor = new Color();

        // Render base layer
        tintedColor.set(originalColor).mul(currentTint);
        sb.setColor(tintedColor);
        sb.draw(this.baseLayer, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, 1.15F * Settings.scale, 1.15F * Settings.scale, 0.0F, 0, 0, 128, 128, false, false);

        // Render energy layers
        if (enabled) {
            for (int i = 0; i < this.energyLayers.length; ++i) {
                tintedColor.set(originalColor).mul(currentTint);
                sb.setColor(tintedColor);
                sb.draw(this.energyLayers[i], current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, 1.15F * Settings.scale, 1.15F * Settings.scale, this.angles[i], 0, 0, 128, 128, false, false);
            }
        } else {
            for (int i = 0; i < this.noEnergyLayers.length; ++i) {
                tintedColor.set(originalColor).mul(currentTint);
                sb.setColor(tintedColor);
                sb.draw(this.noEnergyLayers[i], current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, 1.15F * Settings.scale, 1.15F * Settings.scale, this.angles[i], 0, 0, 128, 128, false, false);
            }
        }

        // Reset the original color
        sb.setColor(originalColor);
    }

    private void updateTint() {
        TheShadowReaper player = (TheShadowReaper) com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
        if (player.isTransformedToRhaast()) {
            currentTint.set(RED_TINT);
        } else if (player.isTransformedToAssassin()) {
            currentTint.set(BLUE_TINT);
        } else {
            float redRatio = (float) TheDarkinScythe.redOrbs / 8f;
            float blueRatio = (float) TheDarkinScythe.blueOrbs / 8f;
            currentTint.set(
                    1f + (RED_TINT.r - 1f) * redRatio + (BLUE_TINT.r - 1f) * blueRatio,
                    1f + (RED_TINT.g - 1f) * redRatio + (BLUE_TINT.g - 1f) * blueRatio,
                    1f + (RED_TINT.b - 1f) * redRatio + (BLUE_TINT.b - 1f) * blueRatio,
                    1f
            );
        }
    }
}