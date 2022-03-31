package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;
import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import com.Suzj.extraenchantments.Utility_Sector.InventoryHelper;
import com.Suzj.extraenchantments.Utility_Sector.TempValue;
import net.minecraft.block.BlockAir;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.*;

public class EnchantmentTakingRisks extends EnchantmentBase {
    public static int interval;

    public Map<Integer, Integer> dimensionMap;

    public static EntityPlayer player = null;
    String lore = I18n.format("Risks.effect.1");
    String lore2 = I18n.format("Risks.effect.2");

    public static Random random = new Random();


    public EnchantmentTakingRisks() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName(extraenchantments.MODID + ".TakingRisks");
        this.setRegistryName("TakingRisks");
        this.dimensionMap = new HashMap<Integer,Integer>();
        //this.dimensionMap = new HashMap<Integer,Integer>();
    }

    @Override
    public boolean isConfigEnabled() {
        return ModConfig.enabled.RiskEnable;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }
/*
    @SubscribeEvent
    public void onUpdate(LivingHurtEvent event){

        EntityLivingBase entityLivingBase = event.getEntityLiving();
        if(entityLivingBase instanceof EntityPlayer) {
            player = (EntityPlayer) entityLivingBase;
        }
        if(player == null)return;
        int level = EnchantmentHelper.getMaxEnchantmentLevel(reg.Risks , player);
        if(level<=0) return;

            ItemStack stack = EnchantmentHelper.getEnchantedItem(reg.Risks,player);
            if(EnchantmentHelper.getEnchantmentLevel(reg.CursePotential, stack) > 0)return;


                itemStack = stack;


        if(!(player.inventory.hasItemStack(itemStack))&&!(isdone)){
            if(itemStack==null)return;
            ItemHandlerHelper.giveItemToPlayer(player , itemStack);
            itemStack.addEnchantment(reg.CursePotential,1);
            isdone = true;
            if(random.nextBoolean()) {
                player.sendMessage(new TextComponentString(lore));
            }else {
                player.sendStatusMessage(new TextComponentString(lore2),false);
            }
        }

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onUpdate(LivingEvent.LivingUpdateEvent event){
        if(event.getEntityLiving() instanceof EntityPlayer){
            player = (EntityPlayer) event.getEntityLiving();
            //if(EnchantmentHelper.getMaxEnchantmentLevel(reg.Risks,player)<0)return;
            itemStack = EnchantmentHelper.getEnchantedItem(reg.Risks,player);
            if(itemStack.isEmpty()){
                return;
            }

            if(!hasItem&&!isdone){
                player.sendMessage(new TextComponentString("doeffct2"));
                ItemHandlerHelper.giveItemToPlayer(player,itemStack.copy());
                player.inventory.addItemStackToInventory(itemStack.copy());
                isdone=true;
            }
        }
    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void giveItem(LivingHurtEvent event){

        if(!isrecord)return;
        EntityLivingBase entityLivingBase = event.getEntityLiving();
        if(entityLivingBase instanceof EntityPlayer) {
            EnchantmentTakingRisks.player = (EntityPlayer) entityLivingBase;
        }
        if(!(entityLivingBase instanceof EntityPlayer))return;
        if(EnchantmentTakingRisks.player == null)return;



        if(TempValue.getItemStack()==null)return;
        String s = String.valueOf(player.inventory.hasItemStack(TempValue.getItemStack()));
        player.sendMessage(new TextComponentString(s));
        String s2 =  TempValue.getItemStack().toString();
        player.sendMessage(new TextComponentString(s2));



        if(!(player.inventory.hasItemStack(TempValue.getItemStack()))){
            hasItem = false;
            player.sendMessage(new TextComponentString("doeffct"));
            final ItemStack itemStack1 = TempValue.getItemStack().copy();
            //ItemHandlerHelper.giveItemToPlayer(EnchantmentTakingRisks.player , itemStack1);


            itemStack1.addEnchantment(reg.CursePotential,1);
            if(EnchantmentTakingRisks.random.nextBoolean()) {
                EnchantmentTakingRisks.player.sendMessage(new TextComponentString(lore));
            }else {
                EnchantmentTakingRisks.player.sendStatusMessage(new TextComponentString(lore2),false);
            }
        }
    }

    @Override
    public String getPrefix() {
        if(isdone){
            return TextFormatting.GRAY.toString();
        }else {
            return TextFormatting.BLUE.toString();
        }
        //TextFormatting.UNDERLINE.toString();
    }

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickItem event){
    player = event.getEntityPlayer();
    ItemStack weapon = player.getHeldItemMainhand();
    itemStack = weapon;
        Item item = player.getHeldItemMainhand().getItem();
    int level = EnchantmentHelper.getEnchantmentLevel(reg.Risks, weapon);
    if (level<=0)return;

    if(EnchantmentHelper.getEnchantmentLevel(reg.CursePotential,weapon) > 0){

        player.sendStatusMessage(new TextComponentString(lore3),true);
        }else {

        player.sendStatusMessage(new TextComponentString(lore4), false);
        TempValue.setItemStack(weapon);
        isrecord = true;

    }

    }

    public static ItemStack itemFromNBT(NBTTagCompound tag) {
        return new ItemStack(tag);
    }*/
@SubscribeEvent(priority=EventPriority.HIGHEST)
public void toss(ItemTossEvent event)
{
    EntityItem item = event.getEntityItem();

    ItemStack theThrown = item.getItem().copy();

    int l = EnchantmentHelper.getEnchantmentLevel(this, theThrown);
    if(l <= 0)
    {
        return;
    }

    EntityPlayer player = event.getPlayer();

    if(!player.isCreative())
    {
        boolean flag = player.inventory.addItemStackToInventory(theThrown);

        if(!flag)
        {
            EntityItem entityItem = player.entityDropItem(theThrown, 1.3f);
            entityItem.setOwner(player.getName());
            entityItem.setNoPickupDelay();
            entityItem.setEntityInvulnerable(true);

        }
        else
        {

        }
        if(random.nextBoolean()) {
            player.sendMessage(new TextComponentString(lore));
        }else {
            player.sendMessage(new TextComponentString(lore2));
        }
        event.setCanceled(true);
    }
}

    @SubscribeEvent
    public void onExist(TickEvent.WorldTickEvent event)
    {
        if(event.phase == TickEvent.Phase.START)
            return;

        //Manage dimension timers
        int dimension = event.world.provider.getDimension();
        Integer dimensionCounterObj = dimensionMap.get(dimension);
        int dimCount = dimensionCounterObj==null? 0 : dimensionCounterObj.intValue();
        dimCount++;

        //Timer check
        //TODO configurable delay
        if(dimCount > 20)
        {
            //Timer is up, reset timer
            dimensionMap.put(dimension, 0);
        }
        else
        {
            //Increment timer and skip tick
            dimensionMap.put(dimension, dimCount);
            return;
        }

        if(interval > 3)
        {
            List<Entity> list = event.world.loadedEntityList;

            //0.4.1
            if(list == null || list.isEmpty())
                return;

            for(int x = 0; x < list.size(); x++)
            {
                Entity listGet = list.get(x);
                if(listGet instanceof EntityItem)
                {
                    EntityItem item = (EntityItem) listGet;
                    ItemStack stack = item.getItem();

                    if(item.ticksExisted <= 3)
                        continue;

                    //TODO Replace checker with Config
                    if(reg.Risks != null && EnchantmentHelper.getEnchantmentLevel(reg.Risks, stack) > 0)
                    {
                        //item.lifespan = 40;
                        //item.setPickupDelay(10);
                    }

                    if(EnchantmentHelper.getEnchantmentLevel(this, stack) > 0)
                    {
                        //0.4.1
                        if(EnchantmentHelper.getEnchantmentLevel(reg.Risks, stack) <= 0)
                            item.setNoDespawn();

                        EntityPlayer thrower = event.world.getClosestPlayerToEntity(item, 32);
                        if(thrower != null && !thrower.isCreative())
                        {
                            boolean flag = thrower.addItemStackToInventory(stack);

                            if(flag)
                            {
                                //TODO proper networking and sync
                                list.remove(x);

                            }
                        }
                    }
                }
            }
        }
        else
        {
            interval++;
        }
    }
}
