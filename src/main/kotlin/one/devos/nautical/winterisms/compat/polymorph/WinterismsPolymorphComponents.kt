package one.devos.nautical.winterisms.compat.polymorph

import com.illusivesoulworks.polymorph.common.components.PolymorphFabricComponents
import one.devos.nautical.winterisms.compat.polymorph.techreborn.TRElectricFurnaceDataComponent
import org.ladysnake.cca.api.v3.block.BlockComponentFactoryRegistry
import org.ladysnake.cca.api.v3.block.BlockComponentInitializer
import techreborn.blockentity.machine.tier1.ElectricFurnaceBlockEntity

class WinterismsPolymorphComponents : BlockComponentInitializer {
    override fun registerBlockComponentFactories(registry: BlockComponentFactoryRegistry) {
        registry.registerFor(
            ElectricFurnaceBlockEntity::class.java,
            PolymorphFabricComponents.BLOCK_ENTITY_RECIPE_DATA
        ) {
            TRElectricFurnaceDataComponent(it)
        }
    }
}