package zach.blockgamemod;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Decoder;
import com.mojang.serialization.Encoder;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;
import org.slf4j.LoggerFactory;

public class BridgeEffect extends StatusEffect {
    public BridgeEffect() {
        super(
                StatusEffectCategory.BENEFICIAL,
                0xFFD700
        );
    }
    @Override
    public  boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity playerEntity = ((PlayerEntity) entity);
            World world = playerEntity.getWorld();
            BlockPos pos = new BlockPos(playerEntity.getX(), playerEntity.getY() + 2, playerEntity.getZ());
            BlockPos pos2 = new BlockPos(playerEntity.getX(), playerEntity.getY() - 1, playerEntity.getZ());
            String block = String.valueOf(world.getBlockState(pos));
            LoggerFactory.getLogger("blockgamemod").info(block);
            BlockState newBlock = world.getBlockState(pos2);
            Block block3 = Blocks.DIAMOND_BLOCK;
            BlockState state = block3.getDefaultState();
            //world.setBlockState(pos, newBlock);
            if (world.isAir(pos2)) {
                world.setBlockState(pos2, state);
            }

        }
    }
}
