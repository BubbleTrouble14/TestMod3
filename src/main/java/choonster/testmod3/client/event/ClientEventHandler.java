package choonster.testmod3.client.event;

import choonster.testmod3.item.ItemModBow;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandler {
	private static final Minecraft MINECRAFT = Minecraft.getMinecraft();

	@SubscribeEvent
	public static void onFOVUpdate(FOVUpdateEvent event) {
		if (event.getEntity().isHandActive() && event.getEntity().getActiveItemStack().getItem() instanceof ItemModBow) {
			float fovModifier = event.getEntity().getItemInUseMaxCount() / 20.0f;

			if (fovModifier > 1.0f) {
				fovModifier = 1.0f;
			} else {
				fovModifier *= fovModifier;
			}

			event.setNewfov(event.getFov() * (1.0f - fovModifier * 0.15f));
		}
	}

	/**
	 * Rotate the player every tick while they're standing on a Block of Iron.
	 * <p>
	 * Test for this thread:
	 * http://www.minecraftforge.net/forum/index.php/topic,36093.0.html
	 *
	 * @param event The event
	 */
	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.END && MINECRAFT.player != null) {
			final EntityPlayer player = MINECRAFT.player;
			if (MINECRAFT.world.getBlockState(new BlockPos(player).down()).getBlock() == Blocks.IRON_BLOCK) {
				player.turn(5, 0);
			}
		}
	}
}
