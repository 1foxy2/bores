package net.foxy.bores.mixin;

import net.foxy.bores.base.ModClientEnums;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.model.HumanoidModel;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HumanoidModel.class)
public class HumanoidModelMixin {

    @Shadow
    public HumanoidModel.ArmPose rightArmPose;

    @Shadow
    public HumanoidModel.ArmPose leftArmPose;

    @ModifyExpressionValue(
            method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/model/HumanoidModel;leftArmPose:Lnet/minecraft/client/model/HumanoidModel$ArmPose;",
                    ordinal = 1,
                    opcode = Opcodes.GETFIELD
            )
    )
    public HumanoidModel.ArmPose removeBob(HumanoidModel.ArmPose original) {
        return original == ModClientEnums.BORE_SINGLE_STANDING_POS.getValue() || original == ModClientEnums.BORE_STANDING_POS.getValue() || rightArmPose == ModClientEnums.BORE_STANDING_POS.getValue() ? HumanoidModel.ArmPose.SPYGLASS : original;
    }

    @ModifyExpressionValue(
            method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/model/HumanoidModel;rightArmPose:Lnet/minecraft/client/model/HumanoidModel$ArmPose;",
                    ordinal = 1,
                    opcode = Opcodes.GETFIELD
            )
    )
    public HumanoidModel.ArmPose removeBobRight(HumanoidModel.ArmPose original) {
        return original == ModClientEnums.BORE_SINGLE_STANDING_POS.getValue() || original == ModClientEnums.BORE_STANDING_POS.getValue() || leftArmPose == ModClientEnums.BORE_STANDING_POS.getValue() ? HumanoidModel.ArmPose.SPYGLASS : original;
    }
}
