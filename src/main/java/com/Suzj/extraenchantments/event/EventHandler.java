package com.Suzj.extraenchantments.event;

import com.Suzj.extraenchantments.Ench.EnchantmentTakingRisks;
import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.Item.ItemRegistryHandler;
import com.Suzj.extraenchantments.Item.ItemTimeWatch;
import com.Suzj.extraenchantments.Utility_Sector.ETeleporter;
import com.Suzj.extraenchantments.Utility_Sector.EUtil;
import com.Suzj.extraenchantments.Utility_Sector.InventoryHelper;
import com.Suzj.extraenchantments.Utility_Sector.NBTHelper;
import com.Suzj.extraenchantments.potion.PotionRegistryHandler;
import com.Suzj.extraenchantments.potion.PotionTeleportSpawn;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.ItemHandlerHelper;

@Mod.EventBusSubscriber
public class EventHandler {
    static int delay = 0;
    static String lore = I18n.format("timewatch.effect.7");

    static String lore3 = I18n.format("timewatch.effect.4");
    static String lore4 = I18n.format("timewatch.effect.5");
    static String lore5 = I18n.format("timewatch.effect.6");
    static boolean isDone = false;
    static boolean isplay = false;
    static World world;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onDamage(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof EntityLivingBase))
            return;

        if (!(event.getSource().getTrueSource() instanceof EntityLivingBase))
            return;

        EntityLivingBase victim = event.getEntityLiving();
        if (victim.isPotionActive(PotionRegistryHandler.POTION_YISHANG)) {
            float seeDamage = event.getAmount();
            float extraDamage = seeDamage * 0.2f;
            event.setAmount(seeDamage + extraDamage);

        }
    }

    @SubscribeEvent
    public static void onUpdate(LivingEvent.LivingUpdateEvent event) {
        if ((event.getEntityLiving() instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            NBTTagCompound persisted = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);

            if (!player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
                player.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, persisted);
            }
            double initialX = player.posX;
            double initialY = player.posY;
            double initialZ = player.posZ;


            if (player.isPotionActive(PotionRegistryHandler.POTION_TELEPORT_SPAWN)) {
                if(player.getDistance(ItemTimeWatch.startPos.getX(),ItemTimeWatch.startPos.getY(),ItemTimeWatch.startPos.getZ()) >1){
                    player.removePotionEffect(MobEffects.BLINDNESS);
                    player.removePotionEffect(MobEffects.NAUSEA);
                    player.removePotionEffect(PotionRegistryHandler.POTION_TELEPORT_SPAWN);
                    player.sendMessage(new TextComponentString(lore));
                    delay=0;
                }


                int maxParticles = 128;

                for (int i = 0; i < maxParticles; ++i) {
                    double scale = (double) i / ((double) maxParticles - 1.0D);
                    double motion = (player.getRNG().nextFloat() - 0.5F) * 0.5F;
                    double posX = initialX + (player.posX - initialX) * scale + (player.getRNG().nextDouble() - 0.5D) * (double) player.width * 2.0D;
                    double posY = initialY + (player.posY - initialY) * scale + player.getRNG().nextDouble() * (double) player.height;
                    double posZ = initialZ + (player.posZ - initialZ) * scale + (player.getRNG().nextDouble() - 0.5D) * (double) player.width * 2.0D;
                    EUtil.spawnParticle(player.world, EnumParticleTypes.ENCHANTMENT_TABLE, posX, posY, posZ, motion);
                    //portal末影粒子
                }

                player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, player.getSoundCategory(), 1.0F, 1.0F);
                isDone = true;
                delay++;

                if(delay>=700*0.25&&delay<700*0.5){
                    player.sendStatusMessage(new TextComponentString(lore3+"25%"),true);
                }else if(delay>=700*0.5&&delay<700*0.75){
                    player.sendStatusMessage(new TextComponentString(lore3+"50%"),true);
                }else if(delay>=0.75*700){
                    player.sendStatusMessage(new TextComponentString(lore3+"75%"),true);
                }

            }

            if (ItemTimeWatch.readFromNBTIsRecord(ItemTimeWatch.timewatch) && isDone && delay >= 700) {
                player.removePotionEffect(PotionRegistryHandler.POTION_TELEPORT_SPAWN);
                if (player instanceof EntityPlayerMP) {
                    if (player.dimension != ItemTimeWatch.readFromNBTDim(ItemTimeWatch.timewatch)) {
                        player.changeDimension(ItemTimeWatch.readFromNBTDim(ItemTimeWatch.timewatch));

                    }
                    world = player.getEntityWorld();

                    ETeleporter.teleportPlayer((EntityPlayerMP) player, null, world, ItemTimeWatch.readFromNBTX(ItemTimeWatch.timewatch) + 0.5D, ItemTimeWatch.readFromNBTY(ItemTimeWatch.timewatch) + 0.1D, ItemTimeWatch.readFromNBTZ(ItemTimeWatch.timewatch) + 0.5D);
                    player.setHealth(ItemTimeWatch.readFromNBTHP());
                    player.experienceTotal = ItemTimeWatch.readFromNBtEXP();
                    delay = 0;
                   player.inventory.mainInventory.clear();
                    for(int j =0;j<36;j++){
                        ItemStack itemStack = NBTHelper.getContainedStack(ItemTimeWatch.timewatch,j);
                        if(ItemStack.areItemsEqual(new ItemStack(ItemRegistryHandler.TIME_WATCH),ItemTimeWatch.readFromNBT(ItemTimeWatch.timewatch).get(j)))continue;
                        ItemHandlerHelper.giveItemToPlayer(player,itemStack);
                        //InventoryHelper.addItemToPlayerInventory(player,itemStack);
                        //player.sendMessage(new TextComponentString(itemStack.toString()));
                    }
                    ItemTimeWatch.getInventoryList().clear();
                    ItemTimeWatch.writeToNBTIsRecord(ItemTimeWatch.timewatch,false);
                    ItemTimeWatch.timewatch.setTagCompound(new NBTTagCompound());
                    player.sendMessage(new TextComponentString(lore4));
                    player.sendMessage(new TextComponentString(lore5));
                     }
                isDone = false;

                }
            }




        }
    }