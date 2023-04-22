package zach.blockgamemod;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;

public class ModBlocks {
    public static final Block PLATINUM_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0f).sounds(BlockSoundGroup.METAL).mapColor(MapColor.IRON_GRAY).requiresTool());
    public static final Block RAW_PLATINUM_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(6.0f).sounds(BlockSoundGroup.METAL).mapColor(MapColor.IRON_GRAY).requiresTool());
    public static final Block PLATINUM_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).requiresTool());
    public static final Block END_PLATINUM_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.OFF_WHITE));
    public static final Block PLATINUM_BLOCK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.METAL).strength(5.0f).sounds(BlockSoundGroup.METAL).mapColor(MapColor.IRON_GRAY).requiresTool());
   // public static final Block TEST_STORAGE_BLOCK = new ChestBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds(BlockSoundGroup.FUNGUS),  () -> {return BlockEntityType.CHEST;});
}
