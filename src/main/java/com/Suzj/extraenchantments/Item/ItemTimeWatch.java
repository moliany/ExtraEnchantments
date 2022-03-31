package com.Suzj.extraenchantments.Item;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import com.Suzj.extraenchantments.Utility_Sector.NBTHelper;
import com.Suzj.extraenchantments.creativetab.TabFMLTutor;
import com.Suzj.extraenchantments.potion.PotionRegistryHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static net.minecraft.entity.MoverType.PLAYER;

public class ItemTimeWatch extends Item {
    private static final List<ItemStack> inventoryList = new ArrayList<>();


    public static List<ItemStack> armorList = new ArrayList<>();
    public static BlockPos startPos;
    static String lore3 = I18n.format("timewatch.effect.3");
    static String lore2 = I18n.format("timewatch.effect.2");
    static String lore = I18n.format("timewatch.effect.1");
    public static ItemStack timewatch = new ItemStack(ItemRegistryHandler.TIME_WATCH);

    public ItemTimeWatch() {
        this.setUnlocalizedName(extraenchantments.MODID + ".timewatch");
        this.setCreativeTab(TabFMLTutor.TAB_FMLTUTOR);
        this.setRegistryName("timewatch");
        this.setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        //ItemTimeWatch.timewatch.setTagCompound(new NBTTagCompound());
        if(player.isSneaking()){
            for(int i =0;i < player.inventory.mainInventory.size();i++){
                inventoryList.add(player.inventory.mainInventory.get(i));

                NBTHelper.updateContainedStack(timewatch,(short) i,player.inventory.mainInventory.get(i),1);
            }
            //armorList.addAll(player.inventory.armorInventory);
            //writeToNBT(timewatch,inventoryList);

            writeToNBTPosX(timewatch,player.posX);
            writeToNBTPosY(timewatch,player.posY);
            writeToNBTPosZ(timewatch,player.posZ);
            writeToNBTHP(player.getHealth());
            writeToNBTIsRecord(timewatch,true);
            writeToNBTDim(timewatch,player.dimension);
            writeToNBtEXP(player.experienceTotal);
            player.sendMessage(new TextComponentString(lore));
            return new ActionResult<>(EnumActionResult.SUCCESS,stack);
        }

        if(!readFromNBTIsRecord(timewatch)){
            player.sendMessage(new TextComponentString(lore2));
            return new ActionResult<>(EnumActionResult.FAIL,stack);
        }
        player.addPotionEffect(new PotionEffect(PotionRegistryHandler.POTION_TELEPORT_SPAWN, 400, 0));
        player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 400, 0));
        player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 400, 0));
        startPos = player.getPosition();

        player.sendMessage(new TextComponentString(lore3));


        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    public static List<ItemStack> getInventoryList(){
        return ItemTimeWatch.inventoryList;
    }

    public static void writeToNBT(ItemStack item, List<ItemStack> theInventory) {
        NBTTagCompound tagcompound = getItemStackNBT(item);
        // Create a new NBT Tag List to store itemstacks as NBT Tags
        NBTTagList items = new NBTTagList();
        ItemStack stack;
        for (int i = 0; i < theInventory.size(); ++i) {
            stack = theInventory.get(i);
            if (!stack.isEmpty() && stack.getCount() == 0) {
                stack = ItemStack.EMPTY;
            }
            if (!stack.isEmpty()) {
                // Make a new NBT Tag Compound to write the itemstack and slot
                // index to
                NBTTagCompound itemTags = new NBTTagCompound();
                itemTags.setInteger("Slot", i);
                // Writes the itemstack in slot(i) to the Tag Compound we just
                // made
                stack.writeToNBT(itemTags);
                // add the tag compound to our tag list
                items.appendTag(itemTags);
            }
        }
        // Add the TagList to the ItemStack's Tag Compound with the name
        // "ItemInventory"
        tagcompound.setTag("ItemInventory", items);
        //item.writeToNBT(tagcompound);
    }

    public static NonNullList<ItemStack> readFromNBT(ItemStack stack) {
        NonNullList<ItemStack> inv = NonNullList.withSize(72, ItemStack.EMPTY);
        if (stack.isEmpty()) {
            return inv;
        }
        NBTTagList items = getItemStackNBT(stack).getTagList("ItemInventory", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < items.tagCount(); ++i) {
            // 1.7.2+ change to items.getCompoundTagAt(i)
            NBTTagCompound item = items.getCompoundTagAt(i);
            int slot = item.getInteger("Slot");
            if (slot >= 0 && slot < 72) {
                inv.set(slot, itemFromNBT(item));
            }
        }
        return inv;
    }

    public static void writeToNBTDim(ItemStack item,int dim){
        NBTTagCompound tagcompound = getItemStackNBT(item);


        tagcompound.setInteger("dim",dim);
    }

    public static int readFromNBTDim(ItemStack itemStack){
        return getItemStackNBT(itemStack).getInteger("dim");
    }

    public static void writeToNBTPosY(ItemStack itemStack ,double y){
        NBTTagCompound tagCompound = getItemStackNBT(itemStack);
        tagCompound.setDouble("y",y);
    }

    public static double readFromNBTY(ItemStack itemStack){
        return getItemStackNBT(itemStack).getDouble("y");
    }

    public static void writeToNBTPosX(ItemStack itemStack ,double x){
        NBTTagCompound tagCompound = getItemStackNBT(itemStack);
        tagCompound.setDouble("x",x);
    }

    public static double readFromNBTX(ItemStack itemStack){
        return getItemStackNBT(itemStack).getDouble("x");
    }
    public static void writeToNBTPosZ(ItemStack itemStack ,double z){
        NBTTagCompound tagCompound = getItemStackNBT(itemStack);
        tagCompound.setDouble("z",z);
    }

    public static double readFromNBTZ(ItemStack itemStack){
        return getItemStackNBT(itemStack).getDouble("z");
    }

    public static void writeToNBTIsRecord(ItemStack itemStack,boolean isRecord){
        NBTTagCompound tagCompound = getItemStackNBT(itemStack);
        tagCompound.setBoolean("isrecord",isRecord);
    }

    public static boolean readFromNBTIsRecord(ItemStack itemStack){
        return getItemStackNBT(itemStack).getBoolean("isrecord");
    }

    public static NBTTagCompound getItemStackNBT(ItemStack held) {
        if (held.getTagCompound() == null) {
            held.setTagCompound(new NBTTagCompound());
        }
        return held.getTagCompound();
    }





    public static ItemStack itemFromNBT(NBTTagCompound tag) {
        return new ItemStack(tag);
    }

    public static void writeToNBtEXP(int exp){
        NBTTagCompound tagCompound = getItemStackNBT(timewatch);
        tagCompound.setInteger("exp",exp);
    }

    public static int readFromNBtEXP(){
        return getItemStackNBT(timewatch).getInteger("exp");
    }

    public static void writeToNBTHP(float hp){
        NBTTagCompound tagCompound = getItemStackNBT(timewatch);
        tagCompound.setFloat("hp",hp);
    }

    public static float readFromNBTHP(){
        return getItemStackNBT(timewatch).getFloat("hp");
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        String lore = I18n.format("timewatch.desc.1");
        String lore2 = I18n.format("timewatch.desc.2");
        String lore3 = I18n.format("timewatch.desc.3");
        String lore4 = I18n.format("timewatch.desc.4");
        tooltip.add(lore);
        tooltip.add(lore2);
        tooltip.add(lore3);
        tooltip.add(lore4);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        return readFromNBTIsRecord(timewatch);
    }
}

