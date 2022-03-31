package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.Item.ItemTimeWatch;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;
import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.UUID;

public class EnchantmentBrokenHeart extends EnchantmentBase {
    int patch = 0;
    InventoryPlayer inv = new InventoryPlayer(null);

    public static final UUID CACHED_UUID = UUID.fromString("e681-134f-4c54-a535-29c3ae5c7a21");

    public EnchantmentBrokenHeart()
    {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_CHEST, new EntityEquipmentSlot[]{EntityEquipmentSlot.CHEST});
        this.setName(extraenchantments.MODID + ".BrokenHeart");
        this.setRegistryName("BrokenHeart");
    }

    @Override
    public boolean isConfigEnabled()
    {
        return ModConfig.enabled.BrokenHeartEnable;
    }

    @Override
    public int getMaxLevel()
    {
        return ModConfig.level.BrokenHeart;
    }
    @Override
    public int getMinEnchantability(int par1)
    {
        return 25 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 75;
    }

    @SubscribeEvent
    public void onHurt(LivingHurtEvent fEvent){

        EntityLivingBase victim = fEvent.getEntityLiving();
        Iterable<ItemStack> a = victim.getArmorInventoryList();


        if(victim==null)return;
        if (!(victim instanceof EntityPlayer))return;
        EntityPlayer player = (EntityPlayer) victim;
        for(int i = 0; i < player.inventory.armorInventory.size(); i++){
            inv.armorInventory.set(i, player.inventory.armorInventory.get(i));


        }
        int i = EnchantmentHelper.getMaxEnchantmentLevel(reg.BrokenHeart, player);
        if(i <= 0){
            RemoveHealth(player);
            return;
        }
        if(fEvent.getAmount()>player.getHealth()){
            AddHealth(player);
            if (patch>9)return;
            player.hurtResistantTime=20;
            fEvent.setCanceled(true);

        }
    }
    private void AddHealth(EntityPlayer fEntity)
    {
        patch++;
        if(patch>9){
            return;
        }
        EntityPlayer player = (EntityPlayer) fEntity;

        IAttributeInstance speedAttr = fEntity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);
        IAttributeInstance maxHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        AttributeModifier modSpeed = new AttributeModifier(CACHED_UUID,"BrokenHeartEffect", -(0.1D * patch), 2);
        speedAttr.removeModifier(modSpeed);
        speedAttr.applyModifier(modSpeed);

        if(speedAttr.getModifier(CACHED_UUID) != null)
            return;

    }

    private void RemoveHealth(EntityLivingBase fEntity)
    {
        int level = EnchantmentHelper.getMaxEnchantmentLevel(reg.BrokenHeart, fEntity);
        IAttributeInstance speedAttr = fEntity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);

        if(speedAttr.getModifier(CACHED_UUID) == null)
            return;

        AttributeModifier modSpeed = new AttributeModifier(CACHED_UUID,"BrokenHeartEffect", 0.2D * level, 2);
        speedAttr.removeModifier(modSpeed);

    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent fEvent) {


        EntityLivingBase victim = fEvent.getEntityLiving();
        if(victim==null)return;
        if (!(victim instanceof EntityPlayer))return;
        EntityPlayer player = (EntityPlayer) victim;
        int level = EnchantmentHelper.getMaxEnchantmentLevel(reg.BrokenHeart, player);
        if(level <= 0)return;
        patch=0;
        //keepAllArmor(player,inv);
        //for(int i = 0; i < player.inventory.armorInventory.size(); i++){
        //	inv.armorInventory.set(i, player.inventory.armorInventory.get(i));
        //	player.inventory.armorInventory.set(i, ItemStack.EMPTY);
//
        //}

    }
    @SubscribeEvent
    public void onSpawn(PlayerEvent.PlayerRespawnEvent e){
        if(e.isEndConquered())
            return;



        EntityPlayer player = e.player;
        //InventoryPlayer inv = new InventoryPlayer(null);
        //keepAllArmor(player,inv);
        World world = player.getEntityWorld();
        BlockPos pos = player.bedLocation;
        for(int i = 0; i < inv.armorInventory.size(); i++){
            //EntityItem itemStack = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), inv.armorInventory.get(i));
            //world.spawnEntity(itemStack);
            //player.inventory.armorInventory.set(inv.armorInventory.get(i), ItemStack.EMPTY);
            player.inventory.addItemStackToInventory(inv.armorInventory.get(i));

        }



    }

}
