package one.devos.nautical.winterisms.mixin.common;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collection;

@Mixin(value = EndDragonFight.class, priority = 1500)
public class EndDragonFightMixin {
    @Shadow
    @Final
    private ServerLevel level;

    @TargetHandler(
            mixin = "com.yungnickyoung.minecraft.betterendisland.mixin.EndDragonFightMixin",
            name = "betterendisland_setDragonKilled",
            prefix = "handler"
    )
    @Redirect(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean cursed(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
        giveItem(net.minecraft.world.item.Items.DRAGON_EGG, level.players(), 1);
        return true;
    }

    @Unique
    private static int giveItem(Item item, Collection<ServerPlayer> targets, int count) {
        ItemStack itemStack = new ItemStack(item, count);
        int i = itemStack.getMaxStackSize();
        int j = i * 100;
        if (count > j) {
            return 0;
        } else {
            for (ServerPlayer serverPlayer : targets) {
                int k = count;

                while (k > 0) {
                    int l = Math.min(i, k);
                    k -= l;
                    ItemStack itemStack2 = new ItemStack(item, count);
                    boolean bl = serverPlayer.getInventory().add(itemStack2);
                    if (bl && itemStack2.isEmpty()) {
                        ItemEntity itemEntity = serverPlayer.drop(itemStack, false);
                        if (itemEntity != null) {
                            itemEntity.makeFakeItem();
                        }

                        serverPlayer.level()
                                .playSound(
                                        null,
                                        serverPlayer.getX(),
                                        serverPlayer.getY(),
                                        serverPlayer.getZ(),
                                        SoundEvents.ITEM_PICKUP,
                                        SoundSource.PLAYERS,
                                        0.2F,
                                        ((serverPlayer.getRandom().nextFloat() - serverPlayer.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F
                                );
                        serverPlayer.containerMenu.broadcastChanges();
                    } else {
                        ItemEntity itemEntity = serverPlayer.drop(itemStack2, false);
                        if (itemEntity != null) {
                            itemEntity.setNoPickUpDelay();
                            itemEntity.setTarget(serverPlayer.getUUID());
                        }
                    }
                }
            }



            return targets.size();
        }
    }
}