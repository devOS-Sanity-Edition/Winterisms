package one.devos.nautical.winterssummerfixes.compat.polymorph.techreborn

import com.illusivesoulworks.polymorph.common.integration.AbstractCompatibilityModule
import net.minecraft.world.item.crafting.RecipeHolder
import net.minecraft.world.item.crafting.SmeltingRecipe
import net.minecraft.world.level.block.entity.BlockEntity
import one.devos.nautical.winterssummerfixes.mixin.compat.techreborn.ElectricFurnaceBlockEntityAccessor
import techreborn.blockentity.machine.tier1.ElectricFurnaceBlockEntity

class TRElectricFurnaceModule : AbstractCompatibilityModule() {
    override fun selectRecipe(blockEntity: BlockEntity, recipe: RecipeHolder<*>): Boolean {
        if (recipe.value is SmeltingRecipe) {
            if (blockEntity is ElectricFurnaceBlockEntity) {
                (blockEntity as ElectricFurnaceBlockEntityAccessor).currentRecipe = recipe.value as SmeltingRecipe
            }
        }

        return super.selectRecipe(blockEntity, recipe)
    }
}