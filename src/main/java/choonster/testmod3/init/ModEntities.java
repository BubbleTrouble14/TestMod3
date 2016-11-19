package choonster.testmod3.init;

import choonster.testmod3.TestMod3;
import choonster.testmod3.entity.EntityBlockDetectionArrow;
import choonster.testmod3.entity.EntityModArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
	public static void registerEntities() {
		registerEntity(EntityModArrow.class, "mod_arrow", 64, 20, false);
		registerEntity(EntityBlockDetectionArrow.class, "block_detection_arrow", 64, 20, false);
	}

	public static void addSpawns() {
		EntityRegistry.addSpawn(EntityGuardian.class, 100, 5, 20, EnumCreatureType.WATER_CREATURE, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.OCEAN));
	}

	private static int entityID = 0;

	/**
	 * Register an entity with the specified tracking values.
	 *
	 * @param entityClass          The entity's class
	 * @param entityName           The entity's unique name
	 * @param trackingRange        The range at which MC will send tracking updates
	 * @param updateFrequency      The frequency of tracking updates
	 * @param sendsVelocityUpdates Whether to send velocity information packets as well
	 */
	private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		final ResourceLocation registryName = new ResourceLocation(TestMod3.MODID, entityName);
		EntityRegistry.registerModEntity(registryName, entityClass, registryName.toString(), entityID++, TestMod3.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
	}
}
