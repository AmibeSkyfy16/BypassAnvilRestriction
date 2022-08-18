package ch.skyfy.bypassanvilrestriction.mixin;

import ch.skyfy.bypassanvilrestriction.config.Configs;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreenHandler.class)
public class AnvilContainerMixin {
    @ModifyConstant(method = "updateResult", constant = @Constant(intValue = 40, ordinal = 2))
    private int modifyInt(int input) {
        System.out.println("new level limit is now : " + Configs.MOD_CONFIG.data.levelLimit);
        return Configs.MOD_CONFIG.data.levelLimit;
    }
}
