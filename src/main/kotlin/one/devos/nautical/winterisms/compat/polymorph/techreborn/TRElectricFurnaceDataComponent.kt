package one.devos.nautical.winterisms.compat.polymorph.techreborn

import com.illusivesoulworks.polymorph.common.components.AbstractBlockEntityRecipeDataComponent
import net.minecraft.core.NonNullList
import net.minecraft.world.item.ItemStack
import techreborn.blockentity.machine.tier1.ElectricFurnaceBlockEntity

class TRElectricFurnaceDataComponent(owner: ElectricFurnaceBlockEntity) :
    AbstractBlockEntityRecipeDataComponent<ElectricFurnaceBlockEntity>(owner) {
    override fun getInput(): NonNullList<ItemStack> {
        return NonNullList.of(ItemStack.EMPTY,this.owner.inventory.getItem(0))
    }
}