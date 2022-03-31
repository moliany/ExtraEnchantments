package com.Suzj.extraenchantments.Ench;

import java.util.Random;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;


import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentWaterWalker;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentSycamore extends EnchantmentBase{
	BlockPos blockPos;
	
	public EnchantmentSycamore()
	{
	super(Rarity.RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET});
    this.setName(extraenchantments.MODID + ".Sycamore");
	this.setRegistryName("Sycamore");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.SycamoreEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
		return ModConfig.level.Sycamore;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 15 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 30;
    }

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerDamage(LivingAttackEvent fEvent) {
		if (!(fEvent.getEntity() instanceof EntityLivingBase))
			return;

		if (!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
			return;

		EntityLivingBase victim = fEvent.getEntityLiving();


		if (victim == null)
			return;
		int level = EnchantmentHelper.getMaxEnchantmentLevel(this, victim);
		if (level <= 0)
			return;



		/*if (victim instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) victim;
			int i = player.dimension;

			String s = String.valueOf(i);

			player.sendMessage(new TextComponentString(s));地狱：-1，主世界：0
		}*/

		Random random = new Random();
		double chance =level*level;


		if (victim instanceof EntityPlayer) {

			EntityPlayer player =(EntityPlayer) victim;
			int spawnDimension = player.getSpawnDimension();
			if (fEvent.getAmount() >= player.getHealth()) {

				if (random.nextInt(100)<=chance) {

					BlockPos bp = player.getBedLocation();
					//
					if (player.dimension==spawnDimension) {
						BlockPos bedSpawnPos = player.getBedSpawnLocation(player.world, bp, false);
						blockPos = bedSpawnPos;
						double distance = Math.sqrt((bp.getX() - player.posX) * (bp.getX() - player.posX) +
								(bp.getY() - player.posY) * (bp.getY() - player.posY));

						if (distance > 5000 && level == 1) return;
					}
					if (player.dimension != spawnDimension && level <= 2) return;

					//int spawnDimension = player.getSpawnDimension();

					if (blockPos==null) {


						BlockPos originalWorldSpawn = player.world.getSpawnPoint();
						player.setPositionAndUpdate(originalWorldSpawn.getX(), originalWorldSpawn.getY(), originalWorldSpawn.getZ());

					} else {
						if(player.dimension!=spawnDimension){
							//teleportToDimension(player,spawnDimension,bp.getX(), bp.getY(), bp.getZ());
							player.changeDimension(0);
							player.sendMessage(new TextComponentString("The thoughts in my mind, beyond space and time"));

						}else {
							player.setPositionAndUpdate(bp.getX(),bp.getY(),bp.getZ());
							player.sendStatusMessage(new TextComponentString("You are telling jokes"), true);
						}




					}
					fEvent.setCanceled(true);
					player.hurtResistantTime = 30;


				}
			}
		}
	}
	public static void teleportToDimension(EntityPlayer player, int dimension, double x, double y, double z) {
		int oldDimension = player.getEntityWorld().provider.getDimension();
		EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
		MinecraftServer server = player.getEntityWorld().getMinecraftServer();
		WorldServer worldServer = server.getWorld(dimension);
		player.addExperienceLevel(0);



		player.setPositionAndUpdate(x, y, z);
		if (oldDimension == 1) {
			// For some reason teleporting out of the end does weird things.
			player.setPositionAndUpdate(x, y, z);
			worldServer.spawnEntity(player);
			worldServer.updateEntityWithOptionalForce(player, false);
		}
	}
	@Override
	public String getPrefix()
	{
		return TextFormatting.BLUE.toString();
	}
}
