package zach.blockgamemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.Arrays;

import static net.minecraft.world.biome.BiomeKeys.*;

public class Main implements ModInitializer {

	//Effect
	public static final StatusEffect BRIDGE = new BridgeEffect();


	//Ore Generation

	private static ConfiguredFeature<?, ?> END_PLATINUM_ORE_CONFIGURED_FEATURE = new ConfiguredFeature
			(Feature.ORE, new OreFeatureConfig(
					new BlockMatchRuleTest(Blocks.END_STONE),
					//Main.PLATINUM_ORE.getDefaultState()
					ModBlocks.END_PLATINUM_ORE.getDefaultState(),
					3)); // vein size

	public static PlacedFeature END_PLATINUM_ORE_PLACED_FEATURE = new PlacedFeature(
			RegistryEntry.of(END_PLATINUM_ORE_CONFIGURED_FEATURE),
			Arrays.asList(
					CountPlacementModifier.of(1), // number of veins per chunk
					SquarePlacementModifier.of(), // spreading horizontally
					HeightRangePlacementModifier.uniform(YOffset.fixed(20), YOffset.fixed(40))
			)); // height


	//Block entity
	/*
	public class DemoBlockEntity extends BlockEntity {
		public DemoBlockEntity(BlockPos pos, BlockState state) {
			super(Main.DEMO_BLOCK_ENTITY, pos, state);
		}
	}
		public static final BlockEntityType<DemoBlockEntity> DEMO_BLOCK_ENTITY = Registry.register(
				Registries.BLOCK_ENTITY_TYPE,
				new Identifier("tutorial", "demo_block_entity"),
				FabricBlockEntityTypeBuilder.create(DemoBlockEntity::new, DEMO_BLOCK).build()
		);
	}
*/

	@Override
	public void onInitialize() {
		//Effect
		Registry.register(Registry.STATUS_EFFECT, new Identifier("blockgamemod", "bridge"), BRIDGE);
		//Block Entity
		Registry.register(Registry.ITEM, new Identifier("minecraft", "end_portal"), new BlockItem(Blocks.END_PORTAL, new Item.Settings().group(ItemGroup.MISC)));
		//Platinum Block
		Registry.register(Registry.BLOCK, new Identifier("blockgamemod", "platinum_block"), ModBlocks.PLATINUM_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("blockgamemod", "platinum_block"), new BlockItem(ModBlocks.PLATINUM_BLOCK, new FabricItemSettings().rarity(Rarity.EPIC).group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.BLOCK, new Identifier("blockgamemod", "platinum_block_slab"), ModBlocks.PLATINUM_BLOCK_SLAB);
		Registry.register(Registry.ITEM, new Identifier("blockgamemod", "platinum_block_slab"), new BlockItem(ModBlocks.PLATINUM_BLOCK_SLAB, new FabricItemSettings().rarity(Rarity.EPIC).group(ItemGroup.BUILDING_BLOCKS)));


		//Raw Platinum Block
		Registry.register(Registry.BLOCK, new Identifier("blockgamemod", "raw_platinum_block"), ModBlocks.RAW_PLATINUM_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("blockgamemod", "raw_platinum_block"), new BlockItem(ModBlocks.RAW_PLATINUM_BLOCK, new FabricItemSettings().rarity(Rarity.EPIC).group(ItemGroup.BUILDING_BLOCKS)));

		//Platinum Ore
		Registry.register(Registry.BLOCK, new Identifier("blockgamemod", "platinum_ore"), ModBlocks.PLATINUM_ORE);
		Registry.register(Registry.ITEM, new Identifier("blockgamemod", "platinum_ore"), new BlockItem(ModBlocks.PLATINUM_ORE, new FabricItemSettings().rarity(Rarity.EPIC).group(ItemGroup.BUILDING_BLOCKS)));

		//End Platinum Ore
		Registry.register(Registry.BLOCK, new Identifier("blockgamemod", "end_platinum_ore"), ModBlocks.END_PLATINUM_ORE);
		Registry.register(Registry.ITEM, new Identifier("blockgamemod", "end_platinum_ore"), new BlockItem(ModBlocks.END_PLATINUM_ORE, new FabricItemSettings().rarity(Rarity.EPIC).group(ItemGroup.BUILDING_BLOCKS)));

		//Test Storage Block
		/*Registry.register(Registry.BLOCK, new Identifier("blockgamemod", "test_storage_block"), ModBlocks.TEST_STORAGE_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("blockgamemod", "test_storage_block"), new BlockItem(ModBlocks.TEST_STORAGE_BLOCK, new FabricItemSettings().rarity(Rarity.COMMON).group(ItemGroup.REDSTONE)));*/
		//Deepslate Platinum Ore: will probably never add


		//Ore Generation
		/*
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("blockgamemod","overworld_platinum_ore"), OVERWORLD_PLATINUM_ORE_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("blockgamemod", "overworld_platinum_ore"), OVERWORLD_PLATINUM_ORE_PLACED_FEATURE);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("blockgamemod", "overworld_platinum_ore")));
		*/
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier("blockgamemod", "end_platinum_ore"), END_PLATINUM_ORE_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("blockgamemod", "end_platinum_ore"),
				END_PLATINUM_ORE_PLACED_FEATURE);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(END_HIGHLANDS, END_MIDLANDS), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY,
						new Identifier("blockgamemod", "end_platinum_ore")));
	}



	//Items
	//Simple Items
//Raw Platinum
	public static final Item RAW_PLATINUM =
			Registry.register(Registry.ITEM, new Identifier("blockgamemod", "raw_platinum"),
					new Item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.EPIC)));

	//Platinum Ingot
	public static final Item PLATINUM_INGOT =
			Registry.register(Registry.ITEM, new Identifier("blockgamemod", "platinum_ingot"),
					new Item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.EPIC)));

	//Platinum Nugget
	public static final Item PLATINUM_NUGGET =
			Registry.register(Registry.ITEM, new Identifier("blockgamemod", "platinum_nugget"),
					new Item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.EPIC)));

	//Test Food
	public static final FoodComponent TEST = (new FoodComponent.Builder()).hunger(40).saturationModifier(3.0F).alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 0.3F).build();
	public static final Item TEST_FOOD =
			Registry.register(Registry.ITEM, new Identifier("blockgamemod", "test_food"),
					new Item(new FabricItemSettings().group(ItemGroup.FOOD).rarity(Rarity.EPIC).food(TEST).maxCount(69)));


	//Tools
	//Pickaxe
	public static final ToolItem PLATINUM_PICKAXE =
			Registry.register(Registry.ITEM, new Identifier("blockgamemod", "platinum_pickaxe"),
					new PickaxeItem(PlatinumToolMaterial.INSTANCE, 2, -2.8F, new Item.Settings().fireproof().rarity(Rarity.EPIC).group(ItemGroup.TOOLS)));

	//Axe
	public static final ToolItem PLATINUM_AXE =
			Registry.register(Registry.ITEM, new Identifier("blockgamemod", "platinum_axe"),
					new AxeItem(PlatinumToolMaterial.INSTANCE, 7, -2.8F, new Item.Settings().fireproof().rarity(Rarity.EPIC).group(ItemGroup.TOOLS)));

	//Shovel
	public static final ToolItem PLATINUM_SHOVEL =
			Registry.register(Registry.ITEM, new Identifier("blockgamemod", "platinum_shovel"),
					new ShovelItem(PlatinumToolMaterial.INSTANCE, 3, -3F, new Item.Settings().fireproof().rarity(Rarity.EPIC).group(ItemGroup.TOOLS)));

	//Hoe


	public static final ToolItem PLATINUM_HOE =
			Registry.register(Registry.ITEM, new Identifier("blockgamemod", "platinum_hoe"),
					new CustomHoeItem(PlatinumToolMaterial.INSTANCE, 2, -2.8F, new Item.Settings().fireproof().rarity(Rarity.EPIC).group(ItemGroup.TOOLS)));

	//Sword
	public static final ToolItem PLATINUM_SWORD =
			Registry.register(Registry.ITEM, new Identifier("blockgamemod", "platinum_sword"),
					new SwordItem(PlatinumToolMaterial.INSTANCE, 4, -2.4F, new Item.Settings().fireproof().rarity(Rarity.EPIC).group(ItemGroup.COMBAT)));

	//Armor
	private static ArmorMaterial PlatinumArmorMaterial = new zach.blockgamemod.PlatinumArmorMaterial();

//Helmet
	public static  final ArmorItem PLATINUM_HELMET =
		Registry.register(Registry.ITEM, new Identifier("blockgamemod", "platinum_helmet"),
				new ArmorItem(PlatinumArmorMaterial, EquipmentSlot.HEAD, new Item.Settings().fireproof().rarity(Rarity.EPIC).group(ItemGroup.COMBAT)));

	//Chestplate
	public static  final ArmorItem PLATINUM_CHESTPLATE =
			Registry.register(Registry.ITEM, new Identifier("blockgamemod", "platinum_chestplate"),
					new ArmorItem(PlatinumArmorMaterial, EquipmentSlot.CHEST, new Item.Settings().fireproof().rarity(Rarity.EPIC).group(ItemGroup.COMBAT)));

	//Leggings
	public static  final ArmorItem PLATINUM_LEGGINGS =
			Registry.register(Registry.ITEM, new Identifier("blockgamemod", "platinum_leggings"),
					new ArmorItem(PlatinumArmorMaterial, EquipmentSlot.LEGS, new Item.Settings().fireproof().rarity(Rarity.EPIC).group(ItemGroup.COMBAT)));

	//Boots
	public static  final ArmorItem PLATINUM_BOOTS =
			Registry.register(Registry.ITEM, new Identifier("blockgamemod", "platinum_boots"),
					new ArmorItem(PlatinumArmorMaterial, EquipmentSlot.FEET, new Item.Settings().fireproof().rarity(Rarity.EPIC).group(ItemGroup.COMBAT)));


}

