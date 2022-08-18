package ch.skyfy.bypassanvilrestriction.mixin;

import ch.skyfy.bypassanvilrestriction.config.Configs;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public class AnvilContainerMixin {

    @Shadow @Final private Property levelCost;

    @ModifyConstant(method = "updateResult", constant = @Constant(intValue = 40, ordinal = 2))
    private int modifyInt(int input) {
        return Configs.MOD_CONFIG.data.levelLimit;
    }

    @Inject(method = "updateResult", at = @At("TAIL"))
    private void updateResult(CallbackInfo ci) {
        levelCost.set(levelCost.get() - (int)(Configs.MOD_CONFIG.data.nerfIncrementalCostByPercent / 100d *  levelCost.get()));
    }
}
