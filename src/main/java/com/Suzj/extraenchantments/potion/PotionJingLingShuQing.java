package com.Suzj.extraenchantments.potion;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionJingLingShuQing extends Potion {
    public PotionJingLingShuQing(){
        super(false , 0x806144);
        this.setRegistryName(extraenchantments.MODID + ":jinglingshuqing");
        this.setPotionName("effect."+ extraenchantments.MODID+".jinglingshuqing");
    }
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(extraenchantments.MODID + ":textures/gui/jinglingshuqing.png");
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
