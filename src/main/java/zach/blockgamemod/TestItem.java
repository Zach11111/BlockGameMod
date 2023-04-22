package zach.blockgamemod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.explosion.Explosion;
import org.slf4j.LoggerFactory;

public class TestItem extends Item {
    public TestItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        /*PigEntity pigEntity = new PigEntity(EntityType.PIG, world);
        pigEntity.setPos(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ());
        world.spawnEntity(pigEntity);
        pigEntity.damage(DamageSource.GENERIC, 1);*/
        /*BlockPos pos = new BlockPos(playerEntity.getX(), playerEntity.getY() - 1, playerEntity.getZ());
        int power = playerEntity.experienceLevel;
        world.createExplosion(playerEntity, playerEntity.getX(), playerEntity.getBodyY(0.0625), playerEntity.getZ(), power, Explosion.DestructionType.DESTROY);
*/        // world.breakBlock(pos, true , playerEntity);

            LoggerFactory.getLogger("blockgamemod").warn("Right Click Detected");
        playerEntity.sendMessage(Text.of("Right Click Detected"));

        playerEntity.isCreative();
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}