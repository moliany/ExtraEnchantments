package com.Suzj.extraenchantments.Utility_Sector;

import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SycamoreHandler {

    BlockPos blockPos;
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onPlayerDamage(LivingAttackEvent fEvent) {
        if (!(fEvent.getEntity() instanceof EntityLivingBase))
            return;

        if (!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
            return;

        EntityLivingBase victim = fEvent.getEntityLiving();


        if (victim == null)
            return;
        int level = EnchantmentHelper.getMaxEnchantmentLevel(reg.Sycamore, victim);
        if (level <= 0)
            return;



		/*if (victim instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) victim;
			int i = player.dimension;

			String s = String.valueOf(i);

			player.sendMessage(new TextComponentString(s));地狱：-1，主世界：0
		}*/

        //Random random = new Random();
        //double chance =level*level;


        if (victim instanceof EntityPlayer) {

            EntityPlayer player =(EntityPlayer) victim;
            int spawnDimension = player.getSpawnDimension();
            if (fEvent.getAmount() >= player.getHealth()) {

                if (true) {

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
}
