package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.Item.ItemWuTongGuo;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;
import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import com.Suzj.extraenchantments.potion.PotionRegistryHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.MinecraftDummyContainer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;
import java.util.Random;

public class EnchantmentGrieve extends EnchantmentBase {

    public static int cure = 1;
    public static float distance = 0;

    public static EntityPlayer player;

    public EnchantmentGrieve()
    {
        super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName(extraenchantments.MODID + ".Grieve");
        this.setRegistryName("Grieve");

    }

    @Override
    public boolean isConfigEnabled()
    {
        return ModConfig.enabled.GrieveEnable;
    }

    @Override
    public int getMaxLevel()
    {
        return ModConfig.level.Grieve;
    }

    @Override
    public int getMinEnchantability(int par1)
    {
        return  30 + (10*par1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return  super.getMinEnchantability(par1) + 30;
    }

    @SubscribeEvent
    public void onDamege(LivingHurtEvent fEvent){
        if (!fEvent.getSource().damageType.equals("player") && !fEvent.getSource().damageType.equals("mob"))
            return;

        if (!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
            return;

        EntityLivingBase attacker = (EntityLivingBase) fEvent.getSource().getTrueSource();

        if (attacker == null)
            return;

        ItemStack dmgSource = ((EntityLivingBase) fEvent.getSource().getTrueSource()).getHeldItemMainhand();
        if (dmgSource == null)
            return;
        EntityLivingBase victim = fEvent.getEntityLiving();


        String lore = I18n.format("Grieve.effect.1");
        String lore2 = I18n.format("Grieve.effect.2");
        Random random = new Random();
        //World world = victim.getEntityWorld();
        //BlockPos pos = victim.getPosition();
        //Minecraft.getInstance().gameRenderer.showFloatingItem(new ItemStack(VictusItems.TOTEM_HEART_ASPECT));
        //MinecraftForgeClient.getRegionRenderCache(world,pos).particleManager.addEmitter(player, ParticleTypes.TOTEM_OF_UNDYING, 30);
        if(EnchantmentHelper.getEnchantmentLevel(reg.Grieve, dmgSource) > 0 && EnchantmentHelper.getMaxEnchantmentLevel(reg.Grieve, victim)<=0){
            ItemStack itemStack = EnchantmentHelper.getEnchantedItem(reg.Grieve,attacker);
            if(victim.getDistance(attacker) > readFromNBTDistance(itemStack)) return;
            float seeDamage = fEvent.getAmount();
            if(attacker.isPotionActive(PotionRegistryHandler.POTION_YISHANG))return;

            attacker.heal(seeDamage*readFromNBTCure(itemStack)*0.6f);
            //Minecraft.getMinecraft().getItemRenderer().renderItem(player, ItemWuTongGuo,);
            attacker.addPotionEffect(new PotionEffect(PotionRegistryHandler.POTION_YISHANG,100,0));
            if(attacker instanceof EntityPlayer){
                player = (EntityPlayer) attacker;
                if(random.nextBoolean()){
                    player.sendStatusMessage(new TextComponentString(lore),true);
                }else {
                    player.sendStatusMessage(new TextComponentString(lore2),true);
                }
            }

        }

        if(EnchantmentHelper.getMaxEnchantmentLevel(reg.Grieve, victim) > 0){

            float seeDamage = fEvent.getAmount();
            if(victim.isPotionActive(PotionRegistryHandler.POTION_YISHANG))return;
            ItemStack itemStack = EnchantmentHelper.getEnchantedItem(reg.Grieve,victim);
            victim.heal(seeDamage*readFromNBTCure(itemStack));
            victim.addPotionEffect(new PotionEffect(PotionRegistryHandler.POTION_YISHANG,110,0));

            if(victim instanceof EntityPlayer){
                player = (EntityPlayer) victim;
                if(random.nextBoolean()){
                    player.sendStatusMessage(new TextComponentString(lore),true);
                }else {
                    player.sendStatusMessage(new TextComponentString(lore2),true);
                }
            }

        }

    }

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent fEvent){
        if(fEvent.getEntityLiving() == null)
            return;

        EntityLivingBase entityLivingBase = fEvent.getEntityLiving();
        String lore = I18n.format("Grieve.effect.3");
        String lore2 = I18n.format("Grieve.effect.4");
        if(player==null)return;
        if(EnchantmentHelper.getMaxEnchantmentLevel(reg.Grieve , player)<=0)return;
        if(entityLivingBase.getLastDamageSource().getImmediateSource()==player)return;
        if(entityLivingBase.getRevengeTarget()!=player&&entityLivingBase.getLastAttackedEntity()!=player&&entityLivingBase.getAttackingEntity()!=player&&entityLivingBase.getLastDamageSource().getTrueSource()!=player){
            Random random = new Random();
            ItemStack itemStack = EnchantmentHelper.getEnchantedItem(reg.Grieve,player);
            if(random.nextBoolean()){
                writeToNBtCure((readFromNBTCure(itemStack))+1,itemStack);
                player.sendMessage(new TextComponentString(lore));
            }else {
                writeToNBtDistance((readFromNBTDistance(itemStack))+2,itemStack);
                player.sendMessage(new TextComponentString(lore2));
            }

        }


    }
    public static ItemStack itemFromNBT(NBTTagCompound tag) {
        return new ItemStack(tag);
    }

    public static NBTTagCompound getItemStackNBT(ItemStack held) {
        if (held.getTagCompound() == null) {
            held.setTagCompound(new NBTTagCompound());
        }
        return held.getTagCompound();
    }

    public static void writeToNBtCure(int cure,ItemStack itemStack){
        NBTTagCompound tagCompound = getItemStackNBT(itemStack);
        tagCompound.setInteger("cure",cure);
    }

    public static int readFromNBTCure(ItemStack itemStack){
        return getItemStackNBT(itemStack).getInteger("cure");
    }

    public static void writeToNBtDistance(int distance,ItemStack itemStack){
        NBTTagCompound tagCompound = getItemStackNBT(itemStack);
        tagCompound.setInteger("distance",distance);
    }

    public static int readFromNBTDistance(ItemStack itemStack){
        return getItemStackNBT(itemStack).getInteger("distance");
    }
}
