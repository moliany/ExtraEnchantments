package com.Suzj.extraenchantments.potion;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionYiShang extends Potion {
    public PotionYiShang(){
        super(false , 0x806145);
        this.setRegistryName(extraenchantments.MODID + ":yishang");
        this.setPotionName("effect."+ extraenchantments.MODID+".yishang");
    }
    @SubscribeEvent
    public void onDamage(LivingHurtEvent event){
        if (!(event.getEntity() instanceof EntityLivingBase))
            return;

        if (!(event.getSource().getTrueSource() instanceof EntityLivingBase))
            return;

        EntityLivingBase victim = event.getEntityLiving();
        if(victim.isPotionActive(PotionRegistryHandler.POTION_YISHANG)){
            float seeDamage = event.getAmount();
            float extraDamage = seeDamage;
            event.setAmount(seeDamage + extraDamage);
        }
    }
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(extraenchantments.MODID + ":textures/gui/yishang.png");
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect e, Minecraft mc){
        mc.getTextureManager().bindTexture(TEXTURE);
        mc.currentScreen.drawTexturedModalRect(x+6,y+7,0,0,18,18);
    }
    @Override
    public  void renderHUDEffect(int x, int y, PotionEffect e, Minecraft mc, float alpha){
        mc.getTextureManager().bindTexture(TEXTURE);
        mc.ingameGUI.drawTexturedModalRect(x+3,y+3,0,0,18,18);
    }
}
