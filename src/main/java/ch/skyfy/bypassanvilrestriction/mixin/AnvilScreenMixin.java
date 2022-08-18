package ch.skyfy.bypassanvilrestriction.mixin;

import ch.skyfy.bypassanvilrestriction.config.Configs;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {
    @ModifyConstant(method = "drawForeground", constant = @Constant(intValue = 40, ordinal = 0))
    private int modifyInt(int input) {
        return Configs.MOD_CONFIG.data.levelLimit;
    }
}
