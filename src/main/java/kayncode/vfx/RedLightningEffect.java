package kayncode.vfx;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class RedLightningEffect extends LightningEffect {
    public RedLightningEffect(float x, float y) {
        super(x, y);
        this.color = Color.RED.cpy();
    }
}
