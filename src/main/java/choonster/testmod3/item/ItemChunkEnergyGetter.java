package choonster.testmod3.item;

import choonster.testmod3.api.capability.chunkenergy.IChunkEnergy;
import choonster.testmod3.capability.chunkenergy.CapabilityChunkEnergy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

/**
 * An item that tells the player how much energy is stored in their current chunk's {@link IChunkEnergy}.
 *
 * @author Choonster
 */
public class ItemChunkEnergyGetter extends ItemTestMod3 {
	public ItemChunkEnergyGetter() {
		super("chunk_energy_getter");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (!worldIn.isRemote) {
			final Chunk chunk = worldIn.getChunkFromBlockCoords(new BlockPos(playerIn));
			final ChunkPos chunkPos = chunk.getPos();
			final IChunkEnergy chunkEnergy = CapabilityChunkEnergy.getChunkEnergy(worldIn, chunkPos);

			if (chunkEnergy != null) {
				playerIn.sendMessage(new TextComponentTranslation("message.testmod3:chunk_energy.get", chunkPos, chunkEnergy.getEnergyStored()));
			} else {
				playerIn.sendMessage(new TextComponentTranslation("message.testmod3:chunk_energy.not_found", chunkPos));
			}
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
}
