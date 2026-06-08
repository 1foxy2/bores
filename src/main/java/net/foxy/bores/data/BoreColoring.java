package net.foxy.bores.data;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.foxy.bores.base.ModRecipeSerializers;
import net.foxy.bores.item.BoreItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class BoreColoring extends CustomRecipe {
    public static final BoreColoring INSTANCE = new BoreColoring();
    public static final MapCodec<BoreColoring> MAP_CODEC = MapCodec.unit(INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, BoreColoring> STREAM_CODEC =
            StreamCodec.unit(INSTANCE);
    public static final RecipeSerializer<BoreColoring> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
    public static final List<DyeColor> ALLOWED_COLORS = List.of(
            DyeColor.WHITE,
            DyeColor.YELLOW,
            DyeColor.LIME,
            DyeColor.GRAY,
            DyeColor.GREEN,
            DyeColor.RED,
            DyeColor.BLACK,
            DyeColor.BLUE
    );

    public BoreColoring() {}

    public boolean matches(CraftingInput input, Level level) {
        int i = 0;
        int j = 0;

        for (int k = 0; k < input.size(); k++) {
            ItemStack itemstack = input.getItem(k);
            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof BoreItem) {
                    i++;
                } else {
                    DyeColor dyeColor = itemstack.get(DataComponents.DYE);
                    if (dyeColor == null || !ALLOWED_COLORS.contains(dyeColor)) {
                        return false;
                    }

                    j++;
                }

                if (j > 1 || i > 1) {
                    return false;
                }
            }
        }

        return i == 1 && j == 1;
    }

    public ItemStack assemble(CraftingInput input) {
        ItemStack itemstack = ItemStack.EMPTY;
        net.minecraft.world.item.DyeColor dyecolor = net.minecraft.world.item.DyeColor.WHITE;

        for (int i = 0; i < input.size(); i++) {
            ItemStack itemstack1 = input.getItem(i);
            if (!itemstack1.isEmpty()) {
                Item item = itemstack1.getItem();
                if (item instanceof BoreItem) {
                    itemstack = itemstack1.copy();
                } else {
                    net.minecraft.world.item.DyeColor tmp = net.minecraft.world.item.DyeColor.getColor(itemstack1);
                    if (tmp != null) dyecolor = tmp;
                }
            }
        }

        itemstack.set(DataComponents.BASE_COLOR, dyecolor);
        return itemstack;
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return ModRecipeSerializers.BORE_COLORING.get();
    }
}
