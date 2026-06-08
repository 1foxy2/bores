package net.foxy.bores.base;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.client.IArmPoseTransformer;

public class ModEnums {
    public static final EnumProxy<ItemDisplayContext> THIRDPERSON_LEFTHAND_SINGLE = new EnumProxy<>(
            ItemDisplayContext.class, -1, "bores:thirdperson_lefthand_single", null
    );
    public static final EnumProxy<ItemDisplayContext> THIRDPERSON_RIGHTHAND_SINGLE = new EnumProxy<>(
            ItemDisplayContext.class, -1, "bores:thirdperson_righthand_single", null
    );
}
