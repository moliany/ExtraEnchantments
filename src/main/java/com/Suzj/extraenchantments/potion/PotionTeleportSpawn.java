package com.Suzj.extraenchantments.potion;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

    public class PotionTeleportSpawn extends Potion {

        public static final String NAME = "teleport_spawn";
        public static final String TAG_NAME = "potion core - spawn teleport";
        public static final String TAG_X = "potion core - spawn teleport x";
        public static final String TAG_Y = "potion core - spawn teleport y";
        public static final String TAG_Z = "potion core - spawn teleport z";
        public static final PotionTeleportSpawn INSTANCE = new PotionTeleportSpawn();

        public static int teleportDelay = 10;

        public PotionTeleportSpawn() {
            super(false, 0x9955FF);
            this.setRegistryName(extraenchantments.MODID + ":TeleportSpawn");
            this.setPotionName("effect."+ extraenchantments.MODID+".TeleportSpawn");
        }

        @Override
        public boolean isInstant() {
            return false;
        }
    }

